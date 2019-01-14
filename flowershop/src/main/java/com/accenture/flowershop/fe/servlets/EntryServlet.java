package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.account.AccountBusinessService;

import com.accenture.flowershop.account.AccountDAOException;
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
@WebServlet(name = "EntryServlet",
        urlPatterns = {"/entry"})
public class EntryServlet extends HttpServlet {

    @Autowired
    AccountBusinessService entryService;


    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = (String) req.getParameter("login");
        String password = (String) req.getParameter("password");
        try {
            Print(req, resp, login, password, false);
        } catch (AccountDAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = (String) req.getParameter("login");
        String password = (String) req.getParameter("password");
        if (req.getParameter("submitRegistrationButton") != null) {
            try {
                Print(req, resp, login, password, true);
            } catch (AccountDAOException e) {
                e.printStackTrace();
            }
        } else if (req.getParameter("submitLoginButton") != null) {
            try {
                Print(req, resp, login, password, false);
            } catch (AccountDAOException e) {
                e.printStackTrace();
            }
        }
    }

    void Print(HttpServletRequest req, HttpServletResponse resp, String login, String password, boolean firstEntry) throws ServletException, IOException, AccountDAOException {
        PrintWriter printWriter = resp.getWriter();

        if (login != null) {
            if (login.length() < 1) {
                if (config.getInitParameter("login") != null) {
                    if (config.getInitParameter("login").length() > 0) {
                        login = config.getInitParameter("login");
                    } else {
                        login = null;
                    }
                } else {
                    login = null;
                }
            }
        } else {
            if (config.getInitParameter("login") != null) {
                if (config.getInitParameter("login").length() > 0) {
                    login = config.getInitParameter("login");
                } else {
                    login = null;
                }
            } else {
                login = null;
            }
        }


        printWriter.println("<html>");
        printWriter.println("<body>");

        if (login != null) {
            if (firstEntry) {
                printWriter.println("<h1 align=center>Thank You, " + login + ", for registering!</h1>");
                //entryService.Registration(login, password)
            }
            else {
                //printWriter.println("<h1 align=center>" + entryService.Login(login, password) + "</h1>");
                if (entryService.Login(login, password)) {
                    printWriter.println("<h1 align=center>Welcome, " + login + "!</h1>");
                }
                else {
                    printWriter.println("<h1 align=center>Login or password not correct!</h1>");
                }
            }
        } else {
            printWriter.println("<h1 align=center>Login or password not correct!</h1>");
        }
        printWriter.println("<form action='index.jsp'>");
        printWriter.println("<p align=center><input type=submit name='toMainPageButton' value='To main page'/></p>");
            printWriter.println("</form>");
        printWriter.println("</body>");
        printWriter.println("</html>");
    }
}