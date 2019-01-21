package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.account.AccountBusinessService;
import com.accenture.flowershop.be.entity.account.AccountType;
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


@Component
@WebServlet(name = "LoginServlet",
        urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Autowired
    private AccountBusinessService accountBusinessService;

    private ServletConfig config;

    @Override
    public void init (ServletConfig config) throws ServletException
    {
        this.config = config;
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig()
    {
        return config;
    }

    @Override
    public void destroy(){}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        login(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
    {
        login(req,resp);
    }


    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
    {
        String login = (String) req.getParameter("login");
        String password = (String) req.getParameter("password");
        PrintWriter printWriter = resp.getWriter();
        HttpSession session = req.getSession();


        printWriter.println("<html>");
        printWriter.println("<body>");
        try {
            if (accountBusinessService.login(login, password)) {
                printWriter.println("<h1 align=center>Welcome, " + login + "!</h1>");
                if(accountBusinessService.isAdmin(login))
                {
                    session.setAttribute("userType", AccountType.ADMIN);
                }
                else {
                    session.setAttribute("userType", AccountType.CUSTOMER);
                }
            }
            else {
                printWriter.println("<h1 align=center>Login or password not correct!</h1>");
            }
        }
        catch (Exception e){
            printWriter.println("<h1 align=center>Error on login!</h1>");
        }

        printWriter.println("<form action='index.jsp'>");
        printWriter.println("<p align=center><input type=submit name='mainPageButton' value='Main page'/></p>");
        printWriter.println("</form>");
        printWriter.println("</body>");
        printWriter.println("</html>");
    }
}
