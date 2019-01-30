package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.account.AccountBusinessService;
import com.accenture.flowershop.be.business.customer.CustomerBusinessService;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.entity.account.Account;
import com.accenture.flowershop.be.entity.account.AccountType;
import com.accenture.flowershop.be.entity.customer.Customer;
import com.accenture.flowershop.fe.dto.flower.Flower;
import com.accenture.flowershop.fe.enums.SessionAttribute;
import com.accenture.flowershop.fe.ws.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Component
@WebServlet(name = "LoginServlet",
        urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Autowired
    private AccountBusinessService accountBusinessService;

    @Autowired
    private CustomerBusinessService customerBusinessService;

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Autowired
    private MapService mapService;

    @Override
    public void init (ServletConfig config) throws ServletException
    {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        login(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        login(req,resp);
    }


    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        PrintWriter printWriter = resp.getWriter();
        HttpSession session = req.getSession();


        printWriter.println("<html><body>");

        try {
            if (accountBusinessService.login(login, password)) {
                session.setAttribute(SessionAttribute.LOGIN.toString(), login);
                if(accountBusinessService.isAdmin(login))
                {
                    session.setAttribute(SessionAttribute.USER_TYPE.toString(), AccountType.ADMIN);
                    resp.sendRedirect("flowers");
                }
                else {
                    session.setAttribute(SessionAttribute.USER_TYPE.toString(), AccountType.CUSTOMER);
                    Customer customer = customerBusinessService.findCustomer(accountBusinessService.findAccount(login));
                    if(customer != null) {
                        session.setAttribute(SessionAttribute.SHOW_ONLY_BASKET.toString(), false);
                        session.setAttribute(SessionAttribute.CUSTOMER.toString(), customer);
                        HashMap<BigInteger, Integer> flowersInBasket = new HashMap<>();
                        session.setAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString(), flowersInBasket);
                        resp.sendRedirect("flowers");
                    }
                    else {
                        printWriter.println("<h1 align=center>Customer for this Account not found!</h1>");
                    }
                }

            }
            else {
                printWriter.println("<h1 align=center>Login or password not correct!</h1>");
            }
        }
        catch (Exception e){
            printWriter.println("<h1 align=center>Error on login!</h1>");
            printWriter.println("<h1 align=center>" + e.getMessage() + "</h1>");
        }

        printWriter.println("<form action='index.jsp'>");
        printWriter.println("<p align=center><input type=submit name='mainPageButton' value='Main page'/></p>");
        printWriter.println("</form>");
        printWriter.println("</body>");
        printWriter.println("</html>");
    }
}
