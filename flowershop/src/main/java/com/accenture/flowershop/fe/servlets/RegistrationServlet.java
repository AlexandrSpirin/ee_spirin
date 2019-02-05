package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.account.AccountBusinessService;
import com.accenture.flowershop.be.business.customer.CustomerBusinessService;
import com.accenture.flowershop.be.entity.account.AccountType;
import com.accenture.flowershop.fe.enums.SessionAttribute;
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


@Component
@WebServlet(name = "RegistrationServlet",
        urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    @Autowired
    private AccountBusinessService accountBusinessService;

    @Autowired
    private CustomerBusinessService customerBusinessService;

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
        showRegistrationInfo(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        showRegistrationInfo(req,resp);
    }


    /**
     * Отображение информации о регистрации покупателя
     * @param req запрос, сессия и параметры которого используются в работе
     * @param resp ответ, printWriter которого используется в работе
     * @throws IOException исключение ввода-вывода, которое будет обрабатываться
     */
    private void showRegistrationInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        //Сессия, атрибуты которой используются для работы
        HttpSession session = req.getSession();
        //Объект для печати объектов в поток вывода текста(отображение на странице)
        PrintWriter printWriter = resp.getWriter();

        //Логин для аккаунта пользователя
        String login = req.getParameter("login");
        //Первый введенный пароль пользователя
        String passwordOne = req.getParameter("passwordOne");
        //Второй введенный пароль пользователя
        String passwordTwo = req.getParameter("passwordTwo");
        //Имя покупателя
        String firstName = req.getParameter("firstName");
        //Фамилия покупателя
        String middleName = req.getParameter("middleName");
        //Отчетсво покупателя
        String lastName = req.getParameter("lastName");
        //Почта покупателя
        String email = req.getParameter("email");
        //Номер телефона покупателя
        String phoneNumber = req.getParameter("phoneNumber");
        //Деньги покупателя
        BigDecimal money = new BigDecimal(req.getParameter("money"));
        //Скидка покупателя
        Integer discount = Integer.valueOf(req.getParameter("discount"));

        printWriter.println("<html><body>");

        try {
            if(session.getAttribute(SessionAttribute.USER_TYPE.toString()) == AccountType.ADMIN) {
                if (passwordOne.equals(passwordTwo)) {
                    if (accountBusinessService.registration(login, passwordOne, AccountType.CUSTOMER)) {
                        if(customerBusinessService.insertCustomer(accountBusinessService.findAccount(login),
                                firstName, middleName, lastName, email, phoneNumber, money, discount)) {
                            printWriter.println("<h1 align=center>" + login + " was registered!</h1>");
                        } else {
                            printWriter.println("<h1 align=center>This account is already in use! Please choose another account.</h1>");
                        }
                    } else {
                        printWriter.println("<h1 align=center>This login is already in use! Please choose another login.</h1>");
                    }
                } else {
                    printWriter.println("<h1 align=center>Different passwords are indicated!</h1>");
                }
                printWriter.println("<form action='index.jsp'>" +
                        "<h1 align=center><input type=submit name='mainPageButton' value='Main page'/></h1>" +
                        "</form>" +
                        "</body>" +
                        "</html>");
            } else {
                printWriter.println("<h1 align=center>Error! You do not have rights to register an account!</h1>" +
                        "<form action='index.jsp'>" +
                        "<h1 align=center><input type=submit name='mainPageButton' value='Main page'/></h1>" +
                        "</form>" +
                        "</body>" +
                        "</html>");
            }
        } catch (Exception e) {
            printWriter.println("<h1 align=center>Error on show flowers</h1>" +
                    "<h2 align=center>Error message:</h2>" +
                    "<h3>" + e.getMessage() + "</h3>" +
                    "<br>" +
                    "<h2 align=center>Error localized message</h2>" +
                    "<h3>" + e.getLocalizedMessage() + "</h3>");
        }
        printWriter.println("</body></html>");
    }
}
