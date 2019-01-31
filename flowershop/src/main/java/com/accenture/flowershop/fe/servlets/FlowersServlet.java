package com.accenture.flowershop.fe.servlets;


import com.accenture.flowershop.be.business.customer.CustomerBusinessService;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.flowerStock.FlowerStockBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.entity.account.AccountType;
import com.accenture.flowershop.fe.dto.customer.Customer;
import com.accenture.flowershop.fe.dto.flower.Flower;
import com.accenture.flowershop.fe.dto.flowerStock.FlowerStock;
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
import javax.transaction.Transaction;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
        showFlowers(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        showFlowers(req,resp);
    }

    public void showFlowers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        PrintWriter printWriter = resp.getWriter();

        AccountType userType = session.getAttribute(SessionAttribute.USER_TYPE.toString()) == null ? null : (AccountType) session.getAttribute(SessionAttribute.USER_TYPE.toString());
        String login = session.getAttribute(SessionAttribute.LOGIN.toString()) == null ? "" : (String) session.getAttribute(SessionAttribute.LOGIN.toString());


        printWriter.println("<html><body>");

        try {
            if (login == "" || userType == null) {
                printWriter.println("Login error! Please login again!" +
                        "<form action='index.jsp' style='display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'>" +
                        "<input type=submit name='toMainPage' value='Main Page'/>" +
                        "</form>");
            } else {
                HashMap<Flower, Integer> flowersInBasket = session.getAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString()) == null ?
                        new HashMap<Flower, Integer>() : (HashMap<Flower, Integer>) session.getAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString());

                Customer customer = new Customer();
                mapService.mapToCustomerDto(customer, (com.accenture.flowershop.be.entity.customer.Customer) session.getAttribute(SessionAttribute.CUSTOMER.toString()));

                Order orderForShow = new Order();
                mapService.mapToOrderDto(orderForShow, (com.accenture.flowershop.be.entity.order.Order) session.getAttribute(SessionAttribute.ORDER_FOR_SHOW.toString()));

                String searchNameText = req.getParameter("searchNameText") == null ? "" : req.getParameter("searchNameText");
                BigDecimal searchMinCostText = req.getParameter("searchMinCostText") == null ? new BigDecimal(0) : new BigDecimal(req.getParameter("searchMinCostText"));
                BigDecimal searchMaxCostText = req.getParameter("searchMaxCostText") == null ? new BigDecimal(0) : new BigDecimal(req.getParameter("searchMaxCostText"));

                if (req.getParameter("searchButton") != null) {
                    session.setAttribute(SessionAttribute.SEARCH.toString(), true);
                }

                if (session.getAttribute(SessionAttribute.SHOW_ONLY_BASKET.toString()) != null) {
                    if (req.getParameter("basketButton") != null) {
                        session.setAttribute(SessionAttribute.SHOW_ORDERS.toString(), false);
                        session.setAttribute(SessionAttribute.SHOW_ONLY_BASKET.toString(), true);
                        session.setAttribute(SessionAttribute.SEARCH.toString(), false);
                        searchNameText = "";
                        searchMinCostText = BigDecimal.valueOf(0);
                        searchMaxCostText = BigDecimal.valueOf(0);
                    }
                    if (req.getParameter("backToShowAllFlowersButton") != null) {
                        session.setAttribute(SessionAttribute.SHOW_ORDERS.toString(), false);
                        session.setAttribute(SessionAttribute.SHOW_ONLY_BASKET.toString(), false);
                        session.setAttribute(SessionAttribute.SEARCH.toString(), false);
                        searchNameText = "";
                        searchMinCostText = BigDecimal.valueOf(0);
                        searchMaxCostText = BigDecimal.valueOf(0);
                    }
                }

                List<Order> orderDtos = new ArrayList();

                if (req.getParameter("ordersButton") != null) {
                    session.setAttribute(SessionAttribute.SHOW_ORDERS.toString(), true);
                    session.setAttribute(SessionAttribute.SHOW_ONLY_BASKET.toString(), false);
                    session.setAttribute(SessionAttribute.SEARCH.toString(), false);
                    searchNameText = "";
                    searchMinCostText = BigDecimal.valueOf(0);
                    searchMaxCostText = BigDecimal.valueOf(0);
                }

                //Находим все типы цветов, подходящие под условия поиска
                List<Flower> flowerDtos = new ArrayList();
                mapService.mapAllFlowerDtos(flowerDtos, flowerBusinessService.findFlowers(searchNameText, searchMinCostText, searchMaxCostText));

                //Находим все позиции склада с цветами, типы которых подходят под условия поиска
                List<FlowerStock> flowerStockDtos = new ArrayList();
                for (int i = 0; i < flowerDtos.size(); i++) {
                    List<FlowerStock> flowerStockDtosWithFlower = new ArrayList();
                    mapService.mapAllFlowerStockDtos(flowerStockDtosWithFlower,
                            flowerStockBusinessService.findFlowerStocksByFlower(mapService.mapToFlowerEntity(flowerDtos.get(i), new com.accenture.flowershop.be.entity.flower.Flower())));
                    for (FlowerStock fP : flowerStockDtosWithFlower) {
                        flowerStockDtos.add(fP);
                    }
                }


                boolean showOnlyBasket = session.getAttribute(SessionAttribute.SHOW_ONLY_BASKET.toString()) == null ? false : (Boolean) session.getAttribute(SessionAttribute.SHOW_ONLY_BASKET.toString());
                boolean showOrders = session.getAttribute(SessionAttribute.SHOW_ORDERS.toString()) == null ? false : (Boolean) session.getAttribute(SessionAttribute.SHOW_ORDERS.toString());

                if(showOrders){
                    if (userType == AccountType.ADMIN) {
                        mapService.mapAllOrderDtos(orderDtos, orderBusinessService.getAllOrders());
                    } else {
                        mapService.mapAllOrderDtos(orderDtos, orderBusinessService.findOrders(mapService.mapToCustomerEntity(customer, new com.accenture.flowershop.be.entity.customer.Customer())));
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
                    printWriter.println("<form action = 'registration.jsp' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'>" +
                            "<input type = 'submit' name = 'registrationButton' value = 'Registration'>" +
                            "</form>" +
                            "<form action = 'flowers' method = 'post' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:0'>");
                } else {
                    printWriter.println("You have money: " + customer.getMoney() + "RUB and discount: " + customer.getDiscount() + "%" +
                            "<form action = 'flowers' method = 'post' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:0'>");
                    if (showOnlyBasket) {
                        printWriter.println("<input type = 'submit' name = 'backToShowAllFlowersButton' value = 'Back to show all Flowers'/>");
                    } else {
                        printWriter.println("<input type = 'submit' name = 'basketButton' value = 'Basket'/>");
                    }
                }
                if (showOrders) {
                    printWriter.println("<input type = 'submit' name = 'backToShowAllFlowersButton' value = 'Back to show all Flowers' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'/>");
                } else {
                    printWriter.println("<input type = 'submit' name = 'ordersButton' value = 'Orders' style = 'display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'/>");
                }
                printWriter.println("</form>" +
                        "</h2>" +
                        "<hr>");
                if (req.getParameter("createOrder") == null) {
                    printWriter.println("<h2 align = center>" +
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
                }
                if (showOrders) {
                    if (orderDtos.isEmpty()) {
                        printWriter.println("<hr><h1 align = center>There is not a single order!</h1>");
                    } else {
                        for (Order o : orderDtos) {
                            if (req.getParameter("Close" + o.getId() + "OrderButton") != null) {
                                o.setStatus("Close");
                                o.setCloseDate(new Date(System.currentTimeMillis()));
                                orderBusinessService.updateOrder(mapService.mapToOrderEntity(o, new com.accenture.flowershop.be.entity.order.Order()));
                            }
                            printWriter.println("<hr>" +
                                    "<h3 align=center>Id: " + o.getId() + "</h3>" +
                                    "<h4 align=center>Status: " + o.getStatus() + "</h4>" +
                                    "<h4 align=center>Create Date: " + o.getCreateDate() + "</h4>" +
                                    "<h4 align=center>Close Date: " + (o.getCloseDate() == null ? "__.__.____" : o.getCloseDate()) + "</h4>" +
                                    "<h4 align=center>Customer: " + o.getCustomer().getFirstName() + " " + o.getCustomer().getMiddleName() +
                                    " " + o.getCustomer().getLastName() + "</h4>" +
                                    "<h4 align=center>Discount: " + o.getDiscount() + "%</h4>" +
                                    "<h4 align=center>Final Price: " + o.getFinalPrice() + "RUB</h4>" +
                                    "<form action = 'flowers' method = 'post'>");
                            if(req.getParameter("show" + o.getId() + "OrderFlowersButton") == null){
                                printWriter.println("<p align=center><input type = 'submit' name = 'show" + o.getId() + "OrderFlowersButton' value = 'Show order flowers'/></p>");
                            } else {
                                printWriter.println("<br><br><h3 align=center>Flowers</h3>");
                                List<OrderFlowers> orderFlowersList = o.getOrderFlowersList();
                                for (OrderFlowers oF: orderFlowersList) {
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
                            if (userType == AccountType.ADMIN) {
                                printWriter.println("<p align=center><input type = 'submit' name = 'close" + o.getId() + "OrderButton' value = 'Close order'/></p>");
                            }
                            printWriter.println("</form>");
                        }
                    }
                } else {
                    if(userType == AccountType.ADMIN){
                        if (flowerStockDtos.isEmpty()) {
                            printWriter.println("<h2 align = center>No flower found with these parameters!</h2>");
                        } else {
                            for (FlowerStock fS: flowerStockDtos) {
                                printWriter.println("<hr>");
                                showMainFlowerInfo(printWriter, fS.getFlower().getName(),  fS.getFlower().getCost().toString(),  fS.getFlowerCount().toString());
                            }
                        }
                    }
                    else if(userType == AccountType.CUSTOMER){
                        if(showOnlyBasket){
                            if(flowersInBasket.isEmpty()){
                                printWriter.println("<hr><h2 align=center>Your basket is empty!</h2>");
                            } else {
                                BigDecimal totalCost = BigDecimal.ZERO;
                                Order order = new Order();
                                boolean createOrder = req.getParameter("createOrder") != null;

                                for (Iterator<HashMap.Entry<Flower, Integer>> it = flowersInBasket.entrySet().iterator(); it.hasNext(); ) {
                                    HashMap.Entry<Flower, Integer> entry = it.next();

                                    //Чтобы получить количество цветов типа, находящегося в корзине, ищем позиции с этим типом цветка на складе
                                    for (FlowerStock fS: flowerStockDtos) {
                                        if(entry.getKey().getId().toString().equals(fS.getFlower().getId().toString())){
                                            Integer flowerCountForChangeBasket = req.getParameter("flower" + entry.getKey().getId() + "CountForChangeBasket") == null ? 0 : Integer.valueOf(req.getParameter("flower" + entry.getKey().getId() + "CountForChangeBasket"));
                                            //Если нажимали кнопку добавить цветы в корзину для цветов этого типа
                                            if (req.getParameter("addFlower" + fS.getFlower().getId() + "ToBasket") != null) {
                                                if(entry.getValue() < fS.getFlowerCount()) {
                                                    Integer newFlowerCountInBasket = entry.getValue() + flowerCountForChangeBasket;
                                                    if (newFlowerCountInBasket > fS.getFlowerCount()) {
                                                        newFlowerCountInBasket = fS.getFlowerCount();
                                                    }
                                                    if(entry.getValue() == 0){
                                                        flowersInBasket.put(fS.getFlower(), newFlowerCountInBasket);
                                                    } else {
                                                        entry.setValue(newFlowerCountInBasket);
                                                    }

                                                    session.setAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString(), flowersInBasket);
                                                }
                                                flowerCountForChangeBasket = 0;
                                            }

                                            boolean entryWasRemoved = false;
                                            //Если нажимали кнопку убрать цветы из корзины для цветов этого типа
                                            if (req.getParameter("removeFlower" + fS.getFlower().getId() + "FromBasket") != null) {
                                                if(entry.getValue() > 0) {
                                                    Integer newFlowerCountInBasket = entry.getValue() - Integer.parseInt(req.getParameter("flower" + fS.getFlower().getId() + "CountForChangeBasket"));
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

                                            if(!entryWasRemoved) {
                                                BigDecimal flowerCostWithDiscount = entry.getKey().getCost().multiply(BigDecimal.valueOf(1 - (customer.getDiscount() * 0.01))).setScale(2, RoundingMode.CEILING);
                                                BigDecimal totalCostForCurrentFlowerType = flowerCostWithDiscount.multiply(new BigDecimal(entry.getValue())).setScale(2, RoundingMode.CEILING);
                                                totalCost = totalCost.add(totalCostForCurrentFlowerType);
                                                OrderFlowers orderFlowers = new OrderFlowers();
                                                orderFlowers.setFlower(entry.getKey());
                                                orderFlowers.setFlowerCount(entry.getValue());
                                                order.addOrderFlowers(orderFlowers);
                                                if (!createOrder) {
                                                    printWriter.println("<hr>");
                                                    showMainFlowerInfo(printWriter, entry.getKey().getName(), flowerCostWithDiscount.toString(), fS.getFlowerCount().toString());
                                                    showCustomerFlowerInfo(printWriter, entry.getKey().getId().toString(), entry.getValue().toString(), totalCostForCurrentFlowerType.toString(), flowerCountForChangeBasket.toString());
                                                }
                                            }
                                        }
                                    }
                                }
                                //После получения итоговой стоимости проверяем нужно и можно ли создать заказ
                                if (createOrder) {
                                    if (totalCost.compareTo(customer.getMoney()) > 0) {
                                        printWriter.println("<h2 align=center>Impossible order! Sorry but you don't have enough money...</h2>" +
                                                "<form action='flowers' method='post'>" +
                                                "<p align=center><input type=submit name='basket' value='Back to Basket' style='display:inline-block; margin-top:12; margin-bottom:0; margin-left:0; margin-right:0'/></p>" +
                                                "</form>");
                                    } else {
                                        printWriter.println("<h2 align=center>Your order is successfully completed!</h2>" +
                                                "<h2 align=center>Total cost: " + totalCost + "RUB");

                                        customer.setMoney(customer.getMoney().subtract(totalCost));
                                        session.setAttribute(SessionAttribute.CUSTOMER.toString(), mapService.mapToCustomerEntity(customer, new com.accenture.flowershop.be.entity.customer.Customer()));
                                        customerBusinessService.updateCustomer(mapService.mapToCustomerEntity(customer, new com.accenture.flowershop.be.entity.customer.Customer()));

                                        for (HashMap.Entry<Flower, Integer> entry : flowersInBasket.entrySet()) {
                                            for (FlowerStock fS: flowerStockDtos) {
                                                if (entry.getKey().getId().toString().equals(fS.getFlower().getId().toString())) {
                                                    fS.setFlowerCount(fS.getFlowerCount()-entry.getValue());
                                                    flowerStockBusinessService.updateFlowerStock(mapService.mapToFlowerStockEntity(fS, new com.accenture.flowershop.be.entity.flowerStock.FlowerStock()));
                                                }
                                            }
                                        }

                                        order.setCustomer(customer);
                                        order.setStatus("Open");
                                        order.setCreateDate(new Date(System.currentTimeMillis()));
                                        order.setDiscount(customer.getDiscount());
                                        order.setFinalPrice(totalCost);
                                        List<com.accenture.flowershop.be.entity.order.OrderFlowers> orderFlowersEntities = mapService.mapAllOrderFlowersEntities(order.getOrderFlowersList(), new ArrayList<com.accenture.flowershop.be.entity.order.OrderFlowers>());

                                        com.accenture.flowershop.be.entity.order.Order mappedOrder = mapService.mapToOrderEntity(order, new com.accenture.flowershop.be.entity.order.Order());

                                        for (com.accenture.flowershop.be.entity.order.OrderFlowers oF: orderFlowersEntities) {
                                            mappedOrder.addOrderFlowers(oF);
                                        }

                                        //com.accenture.flowershop.be.entity.order.Order insertOrder = orderBusinessService.insertOrder(mappedOrder.getCustomer(), mappedOrder.getStatus(), mappedOrder.getOrderFlowersList(), mappedOrder.getCreateDate(), mappedOrder.getCloseDate(),mappedOrder.getDiscount(), mappedOrder.getFinalPrice());
                                        orderBusinessService.insertOrder(mappedOrder);
                                    }
                                } else {
                                    printWriter.println("<hr><h2 align=center>Total cost: " + totalCost + "RUB");
                                    printWriter.println("<form action='flowers' method='post'>" +
                                            "<input type=submit name='createOrder' value='Create Order' style='display:inline-block; margin-top:12; margin-bottom:0; margin-left:0; margin-right:0'/>" +
                                            "</form>");
                                }
                            }
                        }
                        //Если пользователь смотрит весь каталог цветов
                        else {
                            for (FlowerStock fS: flowerStockDtos) {
                                //Проходимся по всем позициям в корзине для получения количества цветов этого типа в корзине
                                Integer flowerCountInBasket = 0;
                                if(!flowersInBasket.isEmpty()) {
                                    for (HashMap.Entry<Flower, Integer> entry : flowersInBasket.entrySet()) {
                                        if (entry.getKey().getId().toString().equals(fS.getFlower().getId().toString())) {
                                            flowerCountInBasket += entry.getValue();
                                        }
                                    }
                                }

                                Integer newFlowerCountInBasket = flowerCountInBasket;
                                Integer flowerCountForChangeBasket = req.getParameter("flower" + fS.getFlower().getId() + "CountForChangeBasket") == null ? 0 : Integer.valueOf(req.getParameter("flower" + fS.getFlower().getId() + "CountForChangeBasket"));

                                //Если нажимали кнопку добавить цветы в корзину для цветов этого типа
                                if (req.getParameter("addFlower" + fS.getFlower().getId() + "ToBasket") != null) {
                                    if(flowerCountInBasket < fS.getFlowerCount()) {
                                        newFlowerCountInBasket += flowerCountForChangeBasket;
                                        if (newFlowerCountInBasket > fS.getFlowerCount()) {
                                            newFlowerCountInBasket = fS.getFlowerCount();
                                        }
                                        if(flowerCountInBasket == 0){
                                            flowersInBasket.put(fS.getFlower(), newFlowerCountInBasket);
                                        } else {
                                            for (HashMap.Entry<Flower, Integer> entry : flowersInBasket.entrySet()) {
                                                if (entry.getKey().getId().toString().equals(fS.getFlower().getId().toString())) {
                                                    entry.setValue(newFlowerCountInBasket);
                                                }
                                            }
                                        }

                                        session.setAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString(), flowersInBasket);
                                    }
                                    flowerCountForChangeBasket = 0;
                                }

                                //Если нажимали кнопку убрать цветы из корзины для цветов этого типа
                                if (req.getParameter("removeFlower" + fS.getFlower().getId() + "FromBasket") != null) {
                                    if(flowerCountInBasket > 0) {
                                        newFlowerCountInBasket -= flowerCountForChangeBasket;
                                        if (newFlowerCountInBasket <= 0) {
                                            newFlowerCountInBasket = 0;
                                            for (Iterator<HashMap.Entry<Flower, Integer>> it = flowersInBasket.entrySet().iterator(); it.hasNext(); ) {
                                                HashMap.Entry<Flower, Integer> entry = it.next();
                                                if (entry.getKey().getId().toString().equals(fS.getFlower().getId().toString())) {
                                                    it.remove();
                                                }
                                            }
                                        } else {
                                            for (Iterator<HashMap.Entry<Flower, Integer>> it = flowersInBasket.entrySet().iterator(); it.hasNext(); ) {
                                                HashMap.Entry<Flower, Integer> entry = it.next();
                                                if (entry.getKey().getId().toString().equals(fS.getFlower().getId().toString())) {
                                                    entry.setValue(newFlowerCountInBasket);
                                                }
                                            }
                                        }
                                        session.setAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString(), flowersInBasket);
                                    }
                                    flowerCountForChangeBasket = 0;
                                }

                                BigDecimal flowerCostWithDiscount = fS.getFlower().getCost().multiply(BigDecimal.valueOf(1 - (customer.getDiscount() * 0.01))).setScale(2, RoundingMode.CEILING);
                                BigDecimal totalCostForCurrentFlowerType = flowerCostWithDiscount.multiply(new BigDecimal(newFlowerCountInBasket)).setScale(2, RoundingMode.CEILING);
                                printWriter.println("<hr>");
                                showMainFlowerInfo(printWriter, fS.getFlower().getName(),  flowerCostWithDiscount.toString(),  fS.getFlowerCount().toString());
                                showCustomerFlowerInfo(printWriter, fS.getFlower().getId().toString(), newFlowerCountInBasket.toString(), totalCostForCurrentFlowerType.toString(), flowerCountForChangeBasket.toString());
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            printWriter.println("<h1 align=center>Error on show flowers</h1>" +
                    "<h2 align=center>Error message:</h1>");
            printWriter.println(e.getMessage());
            printWriter.println("<br><h2 align=center>Error localized message</h1>");
            printWriter.println(e.getLocalizedMessage());
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
                "RUB)<form action='flowers' method='post'>" +
                "<input type=submit name='removeFlower" + flowerId + "FromBasket' value='Remove from Basket' style='display:inline-block; margin-top:12; margin-bottom:0; margin-left:0; margin-right:0'/>" +
                "<input type=text name='flower" + flowerId + "CountForChangeBasket' onkeypress='return event.charCode >= 48 " +
                "&& event.charCode <= 57'  value='" + flowerCountForChangeBasket +
                "' style='display:inline-block; margin-top:12; margin-bottom:0; margin-left:0; margin-right:0'/>" +
                "<input type=submit name='addFlower" + flowerId + "ToBasket' value='Add to Basket' style='display:inline-block; margin-top:12; margin-bottom:0; margin-left:0; margin-right:0'/>" +
                "</form>");
    }
}
