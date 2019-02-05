package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.account.AccountBusinessService;
import com.accenture.flowershop.be.business.customer.CustomerBusinessService;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.entity.account.AccountType;
import com.accenture.flowershop.fe.dto.customer.Customer;
import com.accenture.flowershop.fe.dto.flower.Flower;
import com.accenture.flowershop.fe.enums.SessionAttribute;
import org.dozer.Mapper;
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
import java.util.HashMap;


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
    private Mapper mapper;

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
        showLoginInfo(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        showLoginInfo(req,resp);
    }


    /**
     * Отображение информации о входе пользователя
     *
     * @param req запрос, сессия и параметры которого используются в работе
     * @param resp ответ, printWriter которого используется в работе
     * @throws IOException исключение ввода-вывода, которое будет обрабатываться
     */
    private void showLoginInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        //Сессия, атрибуты которой используются для работы
        HttpSession session = req.getSession();
        //Объект для печати объектов в поток вывода текста(отображение на странице)
        PrintWriter printWriter = resp.getWriter();

        //Логин пользователя
        String login = req.getParameter("login");
        //Пароль пользователя
        String password = req.getParameter("password");

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

                    //Покупатель, которому принадлежит этот аккаунт
                    Customer customer = mapper.map(customerBusinessService.findCustomer(accountBusinessService.findAccount(login)), Customer.class);

                    if(customer != null) {
                        session.setAttribute(SessionAttribute.CUSTOMER.toString(), customer);

                        //Новая корзина для покупателя
                        HashMap<Flower, Integer> flowersInBasket = new HashMap<>();

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
            printWriter.println("<h1 align=center>Error on Login</h1>" +
                    "<h2 align=center>Error message:</h2>" +
                    "<h3>" + e.getMessage() + "</h3>" +
                    "<br>" +
                    "<h2 align=center>Error localized message</h2>" +
                    "<h3>" + e.getLocalizedMessage() + "</h3>");
        }

        printWriter.println("<form action='index.jsp'>" +
                "<p align=center><input type=submit name='mainPageButton' value='Main page'/></p>" +
                "</form>" +
                "</body>" +
                "</html>");
    }
}
