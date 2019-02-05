package com.accenture.flowershop.fe.servlets;


import com.accenture.flowershop.be.business.customer.CustomerBusinessService;
import com.accenture.flowershop.be.business.flowerStock.FlowerStockBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.entity.account.AccountType;
import com.accenture.flowershop.fe.dto.customer.Customer;
import com.accenture.flowershop.fe.dto.flower.Flower;
import com.accenture.flowershop.fe.dto.order.Order;
import com.accenture.flowershop.fe.dto.order.OrderFlowers;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@WebServlet(name = "OrsdersServlet",
        urlPatterns = {"/orders"})
public class OrdersServlet extends HttpServlet {

    @Autowired
    private OrderBusinessService orderBusinessService;

    @Autowired
    private FlowerStockBusinessService flowerStockBusinessService;

    @Autowired
    private CustomerBusinessService customerBusinessService;

    @Autowired
    private Mapper mapper;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        showMainInfo(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        showMainInfo(req, resp);
    }

    public void showMainInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        PrintWriter printWriter = resp.getWriter();

        AccountType userType = session.getAttribute(SessionAttribute.USER_TYPE.toString()) == null ? null : (AccountType) session.getAttribute(SessionAttribute.USER_TYPE.toString());
        String login = session.getAttribute(SessionAttribute.LOGIN.toString()) == null ? "" : (String) session.getAttribute(SessionAttribute.LOGIN.toString());


        //Покупатель, просматривающий свои заказы
        Customer customer = null;
        if(userType == AccountType.CUSTOMER) {
            if (session.getAttribute(SessionAttribute.CUSTOMER.toString()) != null) {
                customer = (com.accenture.flowershop.fe.dto.customer.Customer) session.getAttribute(SessionAttribute.CUSTOMER.toString());
            }
        }

        //Заказ для создания
        Order orderToCreate = null;
        if (session.getAttribute(SessionAttribute.ORDER.toString()) != null) {
            orderToCreate = (com.accenture.flowershop.fe.dto.order.Order) session.getAttribute(SessionAttribute.ORDER.toString());
        }


        printWriter.println("<html><body>");

        try {
            if (login == "" || userType == null || (userType == AccountType.CUSTOMER && customer == null)) {
                printWriter.println("Login error! Please login again!" +
                        "<form action='index.jsp' style='display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'>" +
                        "<input type=submit name='toMainPage' value='Main Page'/>" +
                        "</form>");
            } else {

                //Проверяем нажатие на кнопку перехода от заказов ко всему ассортименту цветов
                if (req.getParameter("allFlowersButton") != null) {
                    resp.sendRedirect("flowers");
                }

                //Проверяем нажатие на кнопку перехода от заказов к корзине
                if (req.getParameter("basketButton") != null) {
                    resp.sendRedirect("basket");
                }


                //Если есть заказ для создания, то показываем информацию о создании заказа
                if (orderToCreate != null) {
                    showOrderCreationInformation(printWriter, session, orderToCreate, userType, login, customer);

                //Если нет заказов для создания, то показываем список заказов пользователя (для администратора - все заказы)
                } else {
                    showAllUserOrderInformation(printWriter, req, userType, login, customer);
                }
            }
        } catch (Exception e) {
            printWriter.println("<h1 align=center>Error on show Orders</h1>" +
                    "<h2 align=center>Error message:</h2>" +
                    "<h3>" + e.getMessage() + "</h3>" +
                    "<br>" +
                    "<h2 align=center>Error localized message</h2>" +
                    "<h3>" + e.getLocalizedMessage() + "</h3>");
        }
        printWriter.println("</body></html>");

    }




    ///////
    ///Показать основную информацию страницы
    ///////
    private void showUserPanel(PrintWriter printWriter, AccountType userType, String login, Customer customer) {
        printWriter.println("<hr>" +
                "<h1 align = center>ORDERS</h1>" +
                "<hr>");
        if(userType == AccountType.ADMIN) {
            printWriter.println("<h2 align = center>Its All Orders, ");
        } else if (userType == AccountType.CUSTOMER) {
            printWriter.println("<h2 align = center>Its Your Orders, ");
        }
        printWriter.println(login + "! " +
                "<form action = 'index.jsp' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'>" +
                "<input type = 'submit' name = 'exitButton' value = 'Exit'/>" +
                "</form>" +
                "<p>");
        if (userType == AccountType.ADMIN) {
            printWriter.println("<form action = 'registration.jsp' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'>" +
                    "<input type = 'submit' name = 'registrationButton' value = 'Registration'>" +
                    "</form>" +
                    "<form action = 'orders' method = 'post' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:0'>");
        } else {
            printWriter.println("You have money: " + customer.getMoney() + "RUB and discount: " + customer.getDiscount() + "%" +
                    "<form action = 'orders' method = 'post' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:0'>" +
                    "<input type = 'submit' name = 'basketButton' value = 'Basket'/>");
        }
        printWriter.println("<input type = 'submit' name = 'allFlowersButton' value = 'All Flowers' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'/>" +
                "</form>" +
                "</h2>");
    }


    ///////
    ///Показать информацию о создании заказа
    ///////
    private void showOrderCreationInformation(PrintWriter printWriter, HttpSession session, Order orderToCreate, AccountType userType, String login, Customer customer) {

        try {
            if (orderBusinessService.createOrder(mapper.map(orderToCreate, com.accenture.flowershop.be.entity.order.Order.class))) {
                customer = mapper.map(customerBusinessService.findCustomer(customer.getId()), Customer.class);
                session.setAttribute(SessionAttribute.CUSTOMER.toString(), customer);

                showUserPanel(printWriter, userType, login, customer);

                session.removeAttribute(SessionAttribute.ORDER.toString());

                session.setAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString(), new HashMap<Flower, Integer>());

                printWriter.println("<hr><h2 align=center>Your order is successfully completed!</h2>" +
                        "<h2 align=center>Total cost: " + orderToCreate.getFinalPrice() + "RUB" +
                        "<form action = 'orders' method='post'>" +
                        "<p><input type = 'submit' name = 'orders' value = 'See orders history'>" +
                        "</form>");
            } else {
                session.removeAttribute(SessionAttribute.ORDER.toString());
                showUserPanel(printWriter, userType, login, customer);
                printWriter.println("<h2 align=center><hr>Impossible order! Sorry but you don't have enough money(" + orderToCreate.getFinalPrice() + "RUB)...</h2>" +
                        "<form action='orders' method='post'>" +
                        "<p align=center><input type=submit name='basketButton' value='Back to Basket' style='display:inline-block; margin-top:12; margin-bottom:0; margin-left:0; margin-right:0'/></p>" +
                        "</form>");
            }
        } catch (Exception e) {
            printWriter.println("<h1 align=center>Error on show order creation information</h1>" +
                    "<h2 align=center>Error message:</h2>" +
                    "<h3>" + e.getMessage() + "</h3>" +
                    "<br>" +
                    "<h2 align=center>Error localized message</h2>" +
                    "<h3>" + e.getLocalizedMessage() + "</h3>");
        }
    }


    ///////
    ///Показать информацию о всех заказах пользователя
    ///////
    private  void showAllUserOrderInformation(PrintWriter printWriter, HttpServletRequest req, AccountType userType, String login, Customer customer) {
        try {
            //Получаем список заказов
            List<Order> orderDtos = new ArrayList<>();
            for (com.accenture.flowershop.be.entity.order.Order o : orderBusinessService.findOrders(customer == null ?
                    null : mapper.map(customer, com.accenture.flowershop.be.entity.customer.Customer.class))) {
                orderDtos.add(mapper.map(o, Order.class));
            }

            //Показываем основную информацию о пользователе
            showUserPanel(printWriter, userType, login, customer);

            if (orderDtos.isEmpty()) {
                printWriter.println("<h1 align = center>There is not a single order!</h1>");
            } else {
                for (Order o : orderDtos) {
                    if (req.getParameter("close" + o.getId() + "OrderButton") != null) {
                        orderBusinessService.closeOrder(mapper.map(o, com.accenture.flowershop.be.entity.order.Order.class));
                        o = mapper.map(orderBusinessService.findOrder(o.getId()), Order.class);
                    }
                    printWriter.println("<hr>" +
                            "<h3 align=center>Id: " + o.getId() + "</h3>" +
                            "<h4 align=center>Status: " + o.getStatus() + "</h4>" +
                            "<h4 align=center>Create Date: " + o.getCreateDate() + "</h4>" +
                            "<h4 align=center>Close Date: " + (o.getCloseDate() == null ? "____-__-__" : o.getCloseDate()) + "</h4>" +
                            "<h4 align=center>Customer: " + o.getCustomer().getFirstName() + " " + o.getCustomer().getMiddleName() +
                            " " + o.getCustomer().getLastName() + "</h4>" +
                            "<h4 align=center>Discount: " + o.getDiscount() + "%</h4>" +
                            "<h4 align=center>Final Price: " + o.getFinalPrice() + "RUB</h4>" +
                            "<form action = 'orders' method = 'post'>");
                    if (req.getParameter("show" + o.getId() + "OrderFlowersButton") == null) {
                        printWriter.println("<p align=center><input type = 'submit' name = 'show" + o.getId() + "OrderFlowersButton' value = 'Show order flowers'/></p>");
                    } else {
                        printWriter.println("<br><br><h3 align=center>Order Flowers</h3>");
                        List<OrderFlowers> orderFlowersList = o.getOrderFlowersList();
                        for (OrderFlowers oF : orderFlowersList) {
                            BigDecimal flowerCostWithDiscount = oF.getFlower().getCost().multiply(BigDecimal.valueOf(1 - (o.getDiscount() * 0.01))).setScale(2, RoundingMode.CEILING);
                            BigDecimal totalCostForCurrentFlowerType = flowerCostWithDiscount.multiply(new BigDecimal(oF.getFlowerCount())).setScale(2, RoundingMode.CEILING);
                            printWriter.println("<br>" +
                                    "<h4 align=center>Name: " + oF.getFlower().getName() + "</h4>" +
                                    "<h5 align=center>Cost: " + flowerCostWithDiscount + "</h5>" +
                                    "<h5 align=center>Count: " + oF.getFlowerCount() + "</h5>" +
                                    "<h5 align=center>Total cost for current flower type: " + totalCostForCurrentFlowerType + "</h5>");

                        }
                        printWriter.println("<p align=center><input type = 'submit' name = 'hide" + o.getId() + "OrderFlowersButton' value = 'Hide order flowers'/></p>");
                    }
                    if (userType == AccountType.ADMIN && o.getStatus() == "Open") {
                        printWriter.println("<p align=center><input type = 'submit' name = 'close" + o.getId() + "OrderButton' value = 'Close order'/></p>");
                    }
                    printWriter.println("</form>");
                }
            }
        } catch (Exception e) {
            printWriter.println("<h1 align=center>Error on show all user order information</h1>" +
                    "<h2 align=center>Error message:</h2>" +
                    "<h3>" + e.getMessage() + "</h3>" +
                    "<br>" +
                    "<h2 align=center>Error localized message</h2>" +
                    "<h3>" + e.getLocalizedMessage() + "</h3>");
        }
    }
}
