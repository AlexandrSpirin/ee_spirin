package com.accenture.flowershop.fe.servlets;


import com.accenture.flowershop.be.business.customer.CustomerBusinessService;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.flowerStock.FlowerStockBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.entity.account.AccountType;
import com.accenture.flowershop.fe.dto.customer.Customer;
import com.accenture.flowershop.fe.dto.flower.Flower;
import com.accenture.flowershop.fe.dto.flowerStock.FlowerStock;
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
@WebServlet(name = "FlowersServlet",
        urlPatterns = {"/flowers"})
public class FlowersServlet extends HttpServlet {

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Autowired
    private FlowerStockBusinessService flowerStockBusinessService;

    @Autowired
    private OrderBusinessService orderBusinessService;

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
        showFlowers(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        showFlowers(req, resp);
    }

    public void showFlowers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        PrintWriter printWriter = resp.getWriter();

        AccountType userType = session.getAttribute(SessionAttribute.USER_TYPE.toString()) == null ? null : (AccountType) session.getAttribute(SessionAttribute.USER_TYPE.toString());
        String login = session.getAttribute(SessionAttribute.LOGIN.toString()) == null ? "" : (String) session.getAttribute(SessionAttribute.LOGIN.toString());


        //Покупатель, просматривающий свои заказы
        Customer customer = null;
        if (session.getAttribute(SessionAttribute.CUSTOMER.toString()) != null) {
            customer = (com.accenture.flowershop.fe.dto.customer.Customer) session.getAttribute(SessionAttribute.CUSTOMER.toString());
        }


        printWriter.println("<html><body>");

        try {
            if (login == "" || userType == null || (userType == AccountType.CUSTOMER && customer == null)) {
                printWriter.println("Login error! Please login again!" +
                        "<form action='index.jsp' style='display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'>" +
                        "<input type=submit name='toMainPage' value='Main Page'/>" +
                        "</form>");
            } else {

                //Мапа для корзины пользователя, в которой хранятся цветки(DTO)/их количество
                HashMap<Flower, Integer> flowersInBasket = session.getAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString()) == null ?
                        new HashMap<Flower, Integer>() : (HashMap<Flower, Integer>) session.getAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString());

                //Проверяем нажатие на кнопку перехода от всего ассортимента цветов к корзине
                if (req.getParameter("basketButton") != null) {
                    resp.sendRedirect("basket");
                }

                //Проверяем нажатие на кнопку перехода от всего ассортимента цветов к заказам
                if (req.getParameter("ordersButton") != null) {
                    resp.sendRedirect("orders");
                }

                //Получаем параметры поиска цветков в корзине, указанные пользователем
                String searchNameText = req.getParameter("searchNameText") == null ? "" : req.getParameter("searchNameText");
                BigDecimal searchMinCostText = req.getParameter("searchMinCostText") == null ? new BigDecimal(0) : new BigDecimal(req.getParameter("searchMinCostText"));
                BigDecimal searchMaxCostText = req.getParameter("searchMaxCostText") == null ? new BigDecimal(0) : new BigDecimal(req.getParameter("searchMaxCostText"));



                //Находим все типы цветов, подходящие под условия поиска
                List<Flower> flowerDtos = new ArrayList();
                mapper.map(flowerBusinessService.findFlowers(searchNameText, searchMinCostText, searchMaxCostText), flowerDtos);

                //Находим все позиции склада с цветами, типы которых подходят под условия поиска
                List<FlowerStock> flowerStockDtos = new ArrayList();
                for (int i = 0; i < flowerDtos.size(); i++) {
                    for(com.accenture.flowershop.be.entity.flowerStock.FlowerStock fS : flowerStockBusinessService.findFlowerStocksByFlower(mapper.map(flowerDtos.get(i), com.accenture.flowershop.be.entity.flower.Flower.class))){
                        flowerStockDtos.add(mapper.map(fS, FlowerStock.class));
                    }
                }

                printWriter.println("<hr>" +
                        "<h1 align = center>FLOWERS</h1>" +
                        "<hr>" +
                        "<h2 align = center>Welcome, " +
                        login + "! " +
                        "<form action = 'index.jsp' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'>" +
                        "<input type = 'submit' name = 'exitButton' value = 'Exit'/>" +
                        "</form>" +
                        "<p>");
                if (userType == AccountType.ADMIN) {
                    printWriter.println("<form action = 'registration.jsp' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:0'>" +
                            "<input type = 'submit' name = 'registrationButton' value = 'Registration'>" +
                            "</form>" +
                            "<form action = 'flowers' method = 'post' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:0'>");
                } else {
                    printWriter.println("You have money: " + customer.getMoney() + "RUB and discount: " + customer.getDiscount() + "%" +
                            "<form action = 'flowers' method = 'post'>" +
                            "<input type = 'submit' name = 'basketButton' value = 'Basket'/>");
                }
                printWriter.println("<input type = 'submit' name = 'ordersButton' value = 'Orders' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'/>" +
                        "</form>" +
                        "</h2>" +
                        "<hr>" +
                        "<h2 align = center>" +
                        "Search" +
                        "</h2>" +
                        "<h3 align = center>" +
                        "<form action = 'flowers' method = 'post'>" +
                        "name " +
                        "<input type=text name='searchNameText' value='" + searchNameText +
                        "' style='display:inline-block; margin-top:0; margin-bottom:0; margin-left:12'/>" +
                        "<p>cost: min" +
                        "<input type = text name='searchMinCostText' onkeypress='return event.charCode >= 48 && event.charCode <= 57'  value='"
                        + searchMinCostText + "' style='display:inline-block; margin-top:0; margin-bottom:0; margin-left:12; margin-right:36'/>" +
                        "max" +
                        "<input type = text name = 'searchMaxCostText' onkeypress = 'return event.charCode >= 48 && event.charCode <= 57' value='"
                        + searchMaxCostText + "' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:12; margin-right:36'/>" +
                        "<p><input type = 'submit' name = 'searchButton' value = 'Search' />" +
                        "</form>" +
                        "</h3>");
                if (flowerStockDtos.isEmpty()) {
                    printWriter.println("<hr><h2 align = center>No flower found with these parameters!</h2>");
                } else {
                    if (userType == AccountType.ADMIN) {
                        for (FlowerStock fS : flowerStockDtos) {
                            printWriter.println("<hr>");
                            showMainFlowerInfo(printWriter, fS.getFlower().getName(), fS.getFlower().getCost().toString(), fS.getFlowerCount().toString());
                        }
                    } else if (userType == AccountType.CUSTOMER) {
                        for (FlowerStock fS : flowerStockDtos) {
                            //Проходимся по всем позициям в корзине для получения количества цветов этого типа в корзине
                            Integer flowerCountInBasket = 0;
                            if (flowersInBasket.get(fS.getFlower()) != null) {
                                flowerCountInBasket = flowersInBasket.get(fS.getFlower());
                            }

                            Integer newFlowerCountInBasket = flowerCountInBasket;

                            Integer flowerCountForChangeBasket = req.getParameter("flower" + fS.getFlower().getId() + "CountForChangeBasket") == null ? 0 : Integer.valueOf(req.getParameter("flower" + fS.getFlower().getId() + "CountForChangeBasket"));
                            if(flowerCountForChangeBasket > 0) {
                                //Если нажали кнопку добавить цветы в корзину для цветов этого типа
                                if (req.getParameter("addFlower" + fS.getFlower().getId() + "ToBasket") != null) {
                                    if (flowerCountInBasket < fS.getFlowerCount()) {
                                        newFlowerCountInBasket += flowerCountForChangeBasket;
                                        if (newFlowerCountInBasket > fS.getFlowerCount()) {
                                            newFlowerCountInBasket = fS.getFlowerCount();
                                        }
                                        flowersInBasket.put(fS.getFlower(), newFlowerCountInBasket);
                                        //if (flowerCountInBasket == 0) {

                                        session.setAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString(), flowersInBasket);
                                    }
                                    flowerCountForChangeBasket = 0;
                                }

                                //Если нажали кнопку убрать цветы из корзины для цветов этого типа
                                if (req.getParameter("removeFlower" + fS.getFlower().getId() + "FromBasket") != null) {
                                    if (flowerCountInBasket > 0) {
                                        newFlowerCountInBasket -= flowerCountForChangeBasket;
                                        if (newFlowerCountInBasket <= 0) {
                                            newFlowerCountInBasket = 0;
                                            if (flowersInBasket.get(fS.getFlower()) != null) {
                                                flowersInBasket.remove(fS.getFlower());
                                            }
                                        } else {
                                            if (flowersInBasket.get(fS.getFlower()) != null) {
                                                flowersInBasket.put(fS.getFlower(), newFlowerCountInBasket);
                                            }
                                        }
                                        session.setAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString(), flowersInBasket);
                                    }
                                    flowerCountForChangeBasket = 0;
                                }
                            }

                            BigDecimal flowerCostWithDiscount = fS.getFlower().getCost().multiply(BigDecimal.valueOf(1 - (customer.getDiscount() * 0.01))).setScale(2, RoundingMode.CEILING);
                            BigDecimal totalCostForCurrentFlowerType = flowerCostWithDiscount.multiply(new BigDecimal(newFlowerCountInBasket)).setScale(2, RoundingMode.CEILING);
                            printWriter.println("<hr>");
                            showMainFlowerInfo(printWriter, fS.getFlower().getName(), flowerCostWithDiscount.toString(), fS.getFlowerCount().toString());
                            showCustomerFlowerInfo(printWriter, fS.getFlower().getId().toString(), newFlowerCountInBasket.toString(), totalCostForCurrentFlowerType.toString(), flowerCountForChangeBasket.toString());
                        }
                    }
                }
            }
        } catch (Exception e) {
            printWriter.println("<h1 align=center>Error on show Flowers</h1>" +
                    "<h2 align=center>Error message:</h2>" +
                    "<h3>" + e.getMessage() + "</h3>" +
                    "<br>" +
                    "<h2 align=center>Error localized message</h2>" +
                    "<h3>" + e.getLocalizedMessage() + "</h3>");
        }
        printWriter.println("</body></html>");
    }


    private void showMainFlowerInfo(PrintWriter printWriter, String name, String cost, String countOnStocks) {
        printWriter.println("<h3 align=center>Name: " + name + "</h3>" +
                "<h4 align=center>Cost: " + cost + "RUB</h4>" +
                "<h4 align=center>Count on stocks: " + countOnStocks);
    }

    private void showCustomerFlowerInfo(PrintWriter printWriter, String flowerId, String flowerCountInBasket,
                                        String totalCostForCurrentFlowerType, String flowerCountForChangeBasket) {
        printWriter.println(" (" + flowerCountInBasket + " in Your Basket = " + totalCostForCurrentFlowerType +
                "RUB)<form action='flowers' method='post'>" +
                "<input type=submit name='removeFlower" + flowerId + "FromBasket' value='Remove from Basket' style='display:inline-block; margin-top:12; margin-bottom:0; margin-left:0; margin-right:0'/>" +
                "<input type=text name='flower" + flowerId + "CountForChangeBasket' onkeypress='return event.charCode >= 48 " +
                "&& event.charCode <= 57'  value='" + flowerCountForChangeBasket +
                "' style='display:inline-block; margin-top:12; margin-bottom:0; margin-left:0; margin-right:0'/>" +
                "<input type=submit name='addFlower" + flowerId + "ToBasket' value='Add to Basket' style='display:inline-block; margin-top:12; margin-bottom:0; margin-left:0; margin-right:0'/>" +
                "</form>");
    }
}
