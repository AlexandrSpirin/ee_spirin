package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.AccountBusinessService;
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
@WebServlet(name = "LoginServlet",
        urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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

        printWriter.println("<html>");
        printWriter.println("<body>");
        try {
            if (entryService.login(login, password)) {
                printWriter.println("<h1 align=center>Welcome, " + login + "!</h1>");
            }
            else {
                printWriter.println("<h1 align=center>Login or password not correct!</h1>");
            }
        }
        catch (Exception e){
            printWriter.println("<h1 align=center>Error on login!</h1>");
            printWriter.println("<h1 align=center>Login: " + login + "</h1>");
            printWriter.println("<h1 align=center>Password: " + password + "</h1>");
        }

        printWriter.println("<form action='index.jsp'>");
        printWriter.println("<p align=center><input type=submit name='toMainPageButton' value='To main page'/></p>");
        printWriter.println("</form>");
        printWriter.println("</body>");
        printWriter.println("</html>");
    }
}
