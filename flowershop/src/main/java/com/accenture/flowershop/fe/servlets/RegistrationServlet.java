package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.AccountBusinessService;
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
import java.io.IOException;
import java.io.PrintWriter;


@Component
@WebServlet(name = "RegistrationServlet",
        urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    @Autowired
    private AccountBusinessService entryService;

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
        registration(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
    {
        registration(req,resp);
    }

    public void registration(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
    {
        String login = (String) req.getParameter("login");
        String passwordOne = (String) req.getParameter("passwordOne");
        String passwordTwo = (String) req.getParameter("passwordTwo");
        PrintWriter printWriter = resp.getWriter();

        printWriter.println("<html>");
        printWriter.println("<body>");

        if(passwordOne.equals(passwordTwo)) {
            try {
                if (entryService.registration(login, passwordOne, AccountType.CUSTOMER)) {
                    printWriter.println("<h1 align=center>Thank you, " + login + ", for registration!</h1>");
                } else {
                    printWriter.println("<h1 align=center>This login is already in use! Please choose another login.</h1>");
                }
            }
            catch (Exception e){
                printWriter.println("<h1 align=center>Error on registration!</h1>");
            }
        }
        else {
            printWriter.println("<h1 align=center>Different passwords are indicated!</h1>");
        }

        printWriter.println("<form action='index.jsp'>");
        printWriter.println("<p align=center><input type=submit name='toMainPageButton' value='To main page'/></p>");
        printWriter.println("</form>");
        printWriter.println("</body>");
        printWriter.println("</html>");
    }
}
