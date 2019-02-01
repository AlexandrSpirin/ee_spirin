package com.accenture.flowershop.fe.servlets;


import com.accenture.flowershop.be.business.flowerStock.FlowerStockBusinessService;
import com.accenture.flowershop.be.entity.account.AccountType;
import com.accenture.flowershop.fe.dto.customer.Customer;
import com.accenture.flowershop.fe.dto.flower.Flower;
import com.accenture.flowershop.fe.dto.order.Order;
import com.accenture.flowershop.fe.dto.order.OrderFlowers;
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
import java.math.RoundingMode;
import java.sql.Date;
import java.util.*;

@Component
@WebServlet(name = "BasketServlet",
        urlPatterns = {"/basket"})
public class BasketServlet extends HttpServlet {
    @Autowired
    private MapService mapService;

    @Autowired
    private FlowerStockBusinessService flowerStockBusinessService;


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
        showBasket(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        showBasket(req,resp);
    }


    public void showBasket(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        PrintWriter printWriter = resp.getWriter();

        AccountType userType = session.getAttribute(SessionAttribute.USER_TYPE.toString()) == null ? null : (AccountType) session.getAttribute(SessionAttribute.USER_TYPE.toString());
        String login = session.getAttribute(SessionAttribute.LOGIN.toString()) == null ? "" : (String) session.getAttribute(SessionAttribute.LOGIN.toString());

        Customer customer = null;
        if(session.getAttribute(SessionAttribute.CUSTOMER.toString()) != null) {
            customer = (com.accenture.flowershop.fe.dto.customer.Customer) session.getAttribute(SessionAttribute.CUSTOMER.toString());
        }


        printWriter.println("<html><body>");

        try {
            //Проверяем атрибуты сессии на корректность
            if (login == "" || userType != AccountType.CUSTOMER || customer == null) {
                printWriter.println("Login error! Please login again!" +
                        "<form action='index.jsp' style='display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'>" +
                        "<input type=submit name='toMainPage' value='Main Page'/>" +
                        "</form>");
            } else {
                //Проверяем нажатие на кнопку перехода от корзины ко всему ассортименту цветов
                if (req.getParameter("allFlowersButton") != null) {
                    resp.sendRedirect("flowers");
                }

                //Проверяем нажатие на кнопку перехода от корзины к заказам покупателя
                if (req.getParameter("ordersButton") != null) {
                    resp.sendRedirect("orders");
                }


                //Мапа для корзины пользователя, в которой хранятся цветки(DTO)/их количество
                HashMap<Flower, Integer> flowersInBasket = session.getAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString()) == null ?
                        new HashMap<Flower, Integer>() : (HashMap<Flower, Integer>) session.getAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString());


                //Получаем параметры поиска цветков в корзине, указанные пользователем
                String searchNameText = req.getParameter("searchNameText") == null ? "" : req.getParameter("searchNameText");
                BigDecimal searchMinCostText = req.getParameter("searchMinCostText") == null ? new BigDecimal(0) : new BigDecimal(req.getParameter("searchMinCostText"));
                BigDecimal searchMaxCostText = req.getParameter("searchMaxCostText") == null ? new BigDecimal(0) : new BigDecimal(req.getParameter("searchMaxCostText"));

                printWriter.println("<hr>" +
                        "<h1 align = center>BASKET</h1>" +
                        "<hr>" +
                        "<h2 align = center>Its Your basket, " +
                        login + "! " +
                        "<form action = 'index.jsp' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'>" +
                        "<input type = 'submit' name = 'exitButton' value = 'Exit'/>" +
                        "</form>" +
                        "<p>" +
                        "You have money: " + customer.getMoney() + "RUB and discount: " + customer.getDiscount() + "%" +
                        "<form action = 'basket' method = 'post'>" +
                        "<input type = 'submit' name = 'allFlowersButton' value = 'All Flowers' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:0'/>" +
                        "<input type = 'submit' name = 'ordersButton' value = 'Orders' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'/>" +
                        "</form>" +
                        "</h2>" +
                        "<hr>" +
                        "<h2 align = center>" +
                        "Search" +
                        "</h2>" +
                        "<h3 align = center>" +
                        "<form action = 'basket' method = 'post'>" +
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

                if (flowersInBasket.isEmpty()) {
                    printWriter.println("<hr><h2 align=center>Your basket is empty!</h2>");
                } else {
                    boolean basketHasMatchingFlowers = false;

                    for (HashMap.Entry<Flower, Integer> entry : flowersInBasket.entrySet()) {
                        if (entry.getValue() > 0) {
                            basketHasMatchingFlowers = true;
                        }
                    }

                    if (!basketHasMatchingFlowers) {
                        printWriter.println("<hr><h2 align=center>Your basket does not have matching flowers!</h2>");
                    } else {
                        //Объявление заказа(DTO)
                        Order order = new Order();

                        BigDecimal totalCost = BigDecimal.ZERO;

                        //Проходим по всей корзине для отображения цветов, находящихся в ней, и рассчета стоимости
                        for (Iterator<HashMap.Entry<Flower, Integer>> it = flowersInBasket.entrySet().iterator(); it.hasNext(); ) {
                            HashMap.Entry<Flower, Integer> entry = it.next();

                            //Количество цветов, на которое нужно изменить количество цветов в корзине
                            Integer flowerCountForChangeBasket = req.getParameter("flower" + entry.getKey().getId() + "CountForChangeBasket") == null ? 0 : Integer.valueOf(req.getParameter("flower" + entry.getKey().getId() + "CountForChangeBasket"));
                            //Количество цветов на складе
                            Integer flowerCountOnStock = 0;
                            if (flowerStockBusinessService.findFlowerStock(entry.getKey().getId()) != null) {
                                flowerCountOnStock = flowerStockBusinessService.findFlowerStock(entry.getKey().getId()).getFlowerCount();
                            }

                            //Был ли удален текущий выбранный элемент корзины после изменения в ней его количества
                            boolean entryWasRemoved = false;

                            //Изменяем количество цветков в корзине только если число для изменения больше 0
                            if(flowerCountForChangeBasket > 0) {
                                //Если нажали кнопку добавить цветы в корзину для цветов этого типа
                                if (req.getParameter("addFlower" + entry.getKey().getId() + "ToBasket") != null) {
                                    if (entry.getValue() < flowerCountOnStock) {
                                        Integer newFlowerCountInBasket = entry.getValue() + flowerCountForChangeBasket;
                                        if (newFlowerCountInBasket > flowerCountOnStock) {
                                            newFlowerCountInBasket = flowerCountOnStock;
                                        }
                                        if (entry.getValue() == 0) {
                                            flowersInBasket.put(entry.getKey(), newFlowerCountInBasket);
                                        } else {
                                            entry.setValue(newFlowerCountInBasket);
                                        }

                                        session.setAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString(), flowersInBasket);
                                    }
                                    flowerCountForChangeBasket = 0;
                                }

                                //Если нажали кнопку убрать цветы из корзины для цветов этого типа
                                if (req.getParameter("removeFlower" + entry.getKey().getId() + "FromBasket") != null) {
                                    if (entry.getValue() > 0) {
                                        Integer newFlowerCountInBasket = entry.getValue() - flowerCountForChangeBasket;
                                        if (newFlowerCountInBasket <= 0) {
                                            it.remove();
                                            entryWasRemoved = true;
                                        } else {
                                            entry.setValue(newFlowerCountInBasket);
                                        }
                                        session.setAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString(), flowersInBasket);
                                    }
                                    flowerCountForChangeBasket = 0;
                                }
                            }

                            //Показываем элемент в корзине только, если его не удалили после изменения его количества в корзине
                            if (!entryWasRemoved) {
                                BigDecimal flowerCostWithDiscount = entry.getKey().getCost().multiply(BigDecimal.valueOf(1 - (customer.getDiscount() * 0.01))).setScale(2, RoundingMode.CEILING);
                                BigDecimal totalCostForCurrentFlowerType = flowerCostWithDiscount.multiply(new BigDecimal(entry.getValue())).setScale(2, RoundingMode.CEILING);
                                totalCost = totalCost.add(totalCostForCurrentFlowerType);
                                OrderFlowers orderFlowers = new OrderFlowers();
                                orderFlowers.setFlower(entry.getKey());
                                orderFlowers.setFlowerCount(entry.getValue());
                                order.addOrderFlowers(orderFlowers);
                                printWriter.println("<hr>");
                                showMainFlowerInfo(printWriter, entry.getKey().getName(), flowerCostWithDiscount.toString(), flowerCountOnStock.toString());
                                showCustomerFlowerInfo(printWriter, entry.getKey().getId().toString(), entry.getValue().toString(), totalCostForCurrentFlowerType.toString(), flowerCountForChangeBasket.toString());
                            }
                        }

                        printWriter.println("<hr><h2 align=center>Total cost: " + totalCost + "RUB" +
                                "<form action='basket' method='post'>" +
                                "<input type=submit name='createOrder' value='Create Order' style='display:inline-block; margin-top:12; margin-bottom:0; margin-left:0; margin-right:0'/>" +
                                "</form>");

                        //Проверяем нажатие на кнопку создания заказа
                        if (req.getParameter("createOrder") != null) {

                            //Дополняем заказ оставшимися параметрами
                            order.setCustomer(customer);
                            order.setStatus("Open");
                            order.setCreateDate(new Date(System.currentTimeMillis()));
                            order.setDiscount(customer.getDiscount());
                            order.setFinalPrice(totalCost);

                            //Добавляем заказ в атрибуты сессии
                            session.setAttribute(SessionAttribute.ORDER.toString(), order);

                            resp.sendRedirect("orders");
                        }
                    }

                }

            }
        }
        catch (Exception e) {
            printWriter.println("<h1 align=center>Error on show Basket</h1>" +
                    "<h2 align=center>Error message:</h2>" +
                    "<h3>" + e.getMessage() + "</h3>" +
                    "<br>" +
                    "<h2 align=center>Error localized message</h2>" +
                    "<h3>" + e.getLocalizedMessage() + "</h3>");
        }
        printWriter.println("</body></html>");
    }


    private void showMainFlowerInfo(PrintWriter printWriter, String name, String cost, String countOnStocks){
        printWriter.println("<h3 align=center>Name: " + name + "</h3>" +
                "<h4 align=center>Cost: " + cost + "RUB</h4>" +
                "<h4 align=center>Count on stocks: " + countOnStocks);
    }

    private void showCustomerFlowerInfo(PrintWriter printWriter, String flowerId, String flowerCountInBasket,
                                        String totalCostForCurrentFlowerType, String flowerCountForChangeBasket){
        printWriter.println(" (" + flowerCountInBasket + " in Your Basket = " + totalCostForCurrentFlowerType +
                "RUB)<form action='basket' method='post'>" +
                "<input type=submit name='removeFlower" + flowerId + "FromBasket' value='Remove from Basket' style='display:inline-block; margin-top:12; margin-bottom:0; margin-left:0; margin-right:0'/>" +
                "<input type=text name='flower" + flowerId + "CountForChangeBasket' onkeypress='return event.charCode >= 48 " +
                "&& event.charCode <= 57'  value='" + flowerCountForChangeBasket +
                "' style='display:inline-block; margin-top:12; margin-bottom:0; margin-left:0; margin-right:0'/>" +
                "<input type=submit name='addFlower" + flowerId + "ToBasket' value='Add to Basket' style='display:inline-block; margin-top:12; margin-bottom:0; margin-left:0; margin-right:0'/>" +
                "</form>");
    }
}
