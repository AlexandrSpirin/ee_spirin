package com.accenture.flowershop.fe.servlets;


import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.flowerStock.FlowerStockBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.entity.account.AccountType;
import com.accenture.flowershop.fe.dto.customer.Customer;
import com.accenture.flowershop.fe.dto.flower.Flower;
import com.accenture.flowershop.fe.dto.flowerStock.FlowerStock;
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
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Date;
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
    private FlowerStockBusinessService flowerPoolBusinessService;

    @Autowired
    private OrderBusinessService orderBusinessService;

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

        String searchNameText = req.getParameter("searchNameText") == null ? "" : req.getParameter("searchNameText");
        BigDecimal searchMinCostText = req.getParameter("searchMinCostText") == null ? new BigDecimal(0) : new BigDecimal(req.getParameter("searchMinCostText"));
        BigDecimal searchMaxCostText = req.getParameter("searchMaxCostText") == null ? new BigDecimal(0) : new BigDecimal(req.getParameter("searchMaxCostText"));

        session.setAttribute(SessionAttribute.SHOW_ONLY_BASKET.toString(), req.getParameter("basketButton") != null ? true :
                session.getAttribute(SessionAttribute.SHOW_ONLY_BASKET.toString()) == null ? false : (Boolean) session.getAttribute(SessionAttribute.SHOW_ONLY_BASKET.toString()));

        session.setAttribute(SessionAttribute.SHOW_ONLY_BASKET.toString(), req.getParameter("backToShowAllFlowersButton") != null ? false :
                session.getAttribute(SessionAttribute.SHOW_ONLY_BASKET.toString()) == null ? false : (Boolean) session.getAttribute(SessionAttribute.SHOW_ONLY_BASKET.toString()));

        AccountType userType = session.getAttribute(SessionAttribute.USER_TYPE.toString()) == null ? null : (AccountType)session.getAttribute(SessionAttribute.USER_TYPE.toString());
        String login = session.getAttribute(SessionAttribute.LOGIN.toString()) == null ? "" : (String)session.getAttribute(SessionAttribute.LOGIN.toString());
        Customer customer = new Customer();
        mapService.mapToCustomerDto(customer, (com.accenture.flowershop.be.entity.customer.Customer)session.getAttribute(SessionAttribute.CUSTOMER.toString()));
        Boolean showOnlyBasket = session.getAttribute(SessionAttribute.SHOW_ONLY_BASKET.toString()) == null ? false : (Boolean) session.getAttribute(SessionAttribute.SHOW_ONLY_BASKET.toString());
        HashMap<BigInteger, Integer> flowersInBasket = session.getAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString()) == null ? new HashMap<BigInteger, Integer>() : (HashMap<BigInteger, Integer>) session.getAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString());


        printWriter.println("<html><body>");

        try {
            if(login==""||userType==null) {
                printWriter.println("Login error! Please login again!" +
                        "<form action='index.jsp' style='display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'>" +
                        "<input type=submit name='toMainPage' value='Main Page'/>" +
                        "</form>" );
            }
            else {
                printWriter.println("<hr>" +
                        "<h1 align=center>FLOWERS</h1>" +
                        "<hr>" +
                        "<h2 align=center>Welcome, " +
                        login + "! "+
                        "<form action='index.jsp' style='display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'>" +
                        "<input type=submit name='exitButton' value='Exit'/>" +
                        "</form>" +
                        "<p>");
                        if(userType == AccountType.ADMIN) {
                            printWriter.println("<form action='registration.jsp' style='display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'>" +
                                    "<input type='submit' name = 'registrationButton' value='Registration'>" +
                                    "</form>");
                        }
                        else {
                            printWriter.println("You have money: " + customer.getMoney() + "RUB and discount: " + customer.getDiscount() + "%" +
                                    "<form action='flowers' method='post' style='display:inline-block; margin-top:0; margin-bottom:0; margin-left:0'>");
                            if (showOnlyBasket) {
                                printWriter.println("<input type=submit name='backToShowAllFlowersButton' value='Back to show all Flowers'/>");
                            } else {
                                printWriter.println("<input type=submit name='basketButton' value='Basket'/>");
                            }
                            printWriter.println("</form>");
                        }
                        printWriter.println("</h2>" +
                        "<hr>" +
                        "<h2 align=center>" +
                        "Search" +
                        "</h2>" +
                        "<h3 align=center>" +
                        "<form action='flowers' method='post'>" +
                        "name " +
                        "<input type=text name='searchNameText' value='" + searchNameText +
                        "' style='display:inline-block; margin-top:0; margin-bottom:0; margin-left:12'/>" +
                        "<p>cost: min" +
                        "<input type=text name='searchMinCostText' onkeypress='return event.charCode >= 48 && event.charCode <= 57'  value='"
                        + searchMinCostText + "' style='display:inline-block; margin-top:0; margin-bottom:0; margin-left:12; margin-right:36'/>" +
                        "max" +
                        "<input type=text name='searchMaxCostText' onkeypress='return event.charCode >= 48 && event.charCode <= 57' value='"
                        + searchMaxCostText + "' style='display:inline-block; margin-top:0; margin-bottom:0; margin-left:12; margin-right:36'/>" +
                        "<p><input type=submit name='searchButton' value='Search' />" +
                        "</form>"  +
                        "</h3>");
                List<Flower> flowerDtos = new ArrayList();
                if (showOnlyBasket) {
                    mapService.mapAllFlowerDtos(flowerDtos, flowerBusinessService.getAllFlowers());
                }
                else {
                    mapService.mapAllFlowerDtos(flowerDtos, flowerBusinessService.findFlowers(searchNameText, searchMinCostText, searchMaxCostText));
                }
                List<FlowerStock> flowerStockDtos = new ArrayList();
                for(int i=0; i < flowerDtos.size(); i++) {
                    List<FlowerStock> flowerStockDtosWithFlower = new ArrayList();
                    mapService.mapAllFlowerStockDtos(flowerStockDtosWithFlower,
                            flowerPoolBusinessService.findFlowerStocksByFlower(mapService.mapToFlowerEntity(flowerDtos.get(i), new com.accenture.flowershop.be.entity.flower.Flower())));
                    for (FlowerStock fP:flowerStockDtosWithFlower) {
                        flowerStockDtos.add(fP);
                    }
                }

                if (flowerDtos.isEmpty()) {
                    printWriter.println("<h1 align=center>No flower found with these parameters!</h1>");
                }
                else {
                    HashMap<BigInteger, Integer> flowersOnStocks = new HashMap<>();
                    for (Flower f : flowerDtos) {
                        //Рассчитываем количество цветов каждого типа в сумме из всех складов
                        int flowerCountOnStocks = 0;
                        for (FlowerStock fP : flowerStockDtos) {
                            if (fP.getFlower().getId() == f.getId()) {
                                flowerCountOnStocks += fP.getFlowerCount();
                            }
                        }
                        flowersOnStocks.put(BigInteger.valueOf(f.getId()), flowerCountOnStocks);

                        //Для пользователя изменяем количество цветов в корзине, если необходимо
                        if (userType == AccountType.CUSTOMER) {
                            if(req.getParameter("addFlower" + f.getId() + "ToBasket") != null){
                                for (HashMap.Entry<BigInteger, Integer> entry : flowersInBasket.entrySet()) {
                                    if (entry.getKey().toString().equals(String.valueOf(f.getId()))) {
                                        Integer flowerCountInBasket = entry.getValue() + Integer.parseInt(req.getParameter("flower" + f.getId() + "CountForChangeBasket"));
                                        if (flowerCountInBasket > flowerCountOnStocks) {
                                            flowerCountInBasket = flowerCountOnStocks;
                                        }
                                        req.setAttribute("flower" + f.getId() + "CountForChangeBasket", "0");
                                        entry.setValue(flowerCountInBasket);
                                        session.setAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString(), flowersInBasket);
                                    }
                                }
                            }
                            if(req.getParameter("removeFlower" + f.getId() + "FromBasket") != null){
                                for (HashMap.Entry<BigInteger, Integer> entry : flowersInBasket.entrySet()) {
                                    if (entry.getKey().toString().equals(String.valueOf(f.getId()))) {
                                        Integer flowerCountInBasket = entry.getValue() - Integer.parseInt(req.getParameter("flower" + f.getId() + "CountForChangeBasket"));
                                        if (flowerCountInBasket < 0) {
                                            flowerCountInBasket = 0;
                                        }
                                        req.setAttribute("flower" + f.getId() + "CountForChangeBasket", "0");
                                        entry.setValue(flowerCountInBasket);
                                        session.setAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString(), flowersInBasket);
                                    }
                                }
                            }

                        }
                    }
                    boolean basketIsEmpty = true;
                    BigDecimal totalCost = BigDecimal.ZERO;
                    for (Flower f : flowerDtos) {
                        //Если админ или покупатель не наблюдающий корзину? то показываем ему основную инфу всего ассортимента цветов, подходящего по параметрам поиска
                        if(userType == AccountType.ADMIN || !showOnlyBasket) {
                            showMainFlowerInfo(printWriter, f.getName(), f.getCost().multiply(BigDecimal.valueOf(1-(customer.getDiscount()*0.01))).setScale(2, RoundingMode.CEILING).toString(), flowersOnStocks.get(BigInteger.valueOf(f.getId())).toString());
                            //Если покупатель, то показываем ему инфу о количестве цветов в корзине для всего ассортимента цветов, подходящего по параметрам поиска
                            if(userType == AccountType.CUSTOMER){
                                Integer flowerCountInBasket = 0;
                                for (HashMap.Entry<BigInteger, Integer> entry : flowersInBasket.entrySet()) {
                                    if (entry.getKey().toString().equals(String.valueOf(f.getId()))) {
                                        flowerCountInBasket = entry.getValue() == null ? 0 : entry.getValue();
                                    }
                                }
                                BigDecimal totalCostForCurrentFlowerType = f.getCost().multiply((new BigDecimal(flowerCountInBasket).multiply(BigDecimal.valueOf(1-(customer.getDiscount()*0.01))))).setScale(2, RoundingMode.CEILING);
                                String flowerCountForChangeBasket = req.getParameter("flower" + f.getId() + "CountForChangeBasket") == null ? "0" : req.getParameter("flower" + f.getId() + "CountForChangeBasket");
                                showCustomerFlowerInfo(printWriter, f.getId().toString(), flowerCountInBasket.toString(), totalCostForCurrentFlowerType.toString(), flowerCountForChangeBasket);
                            }
                        }
                        //Иначе пользователь наблюдающий корзину, то показываем ему всю инфу о цветах в корзине
                        else  {
                            Integer flowerCountInBasket = 0;
                            for (HashMap.Entry<BigInteger, Integer> entry : flowersInBasket.entrySet()) {
                                if (entry.getKey().toString().equals(String.valueOf(f.getId()))) {
                                    flowerCountInBasket = entry.getValue() == null ? 0 : entry.getValue();
                                }
                            }
                            if (flowerCountInBasket > 0) {
                                basketIsEmpty = false;
                                BigDecimal totalCostForCurrentFlowerType = f.getCost().multiply((new BigDecimal(flowerCountInBasket).multiply(BigDecimal.valueOf(1-(customer.getDiscount()*0.01))))).setScale(2, RoundingMode.CEILING);
                                showMainFlowerInfo(printWriter, f.getName(), f.getCost().multiply(BigDecimal.valueOf(1-(customer.getDiscount()*0.01))).setScale(2, RoundingMode.CEILING).toString(), flowersOnStocks.get(BigInteger.valueOf(f.getId())).toString());
                                String flowerCountForChangeBasket = req.getParameter("flower" + f.getId() + "CountForChangeBasket") == null ? "0" : req.getParameter("flower" + f.getId() + "CountForChangeBasket");
                                showCustomerFlowerInfo(printWriter, f.getId().toString(), flowerCountInBasket.toString(), totalCostForCurrentFlowerType.toString(), flowerCountForChangeBasket);
                                totalCost = totalCost.add(totalCostForCurrentFlowerType);
                            }
                        }
                        printWriter.println("</h4>");
                    }
                    if(showOnlyBasket) {
                        if(basketIsEmpty) {
                            printWriter.println("<hr><h2 align=center>Your basket is empty!</h2>");
                        }
                        else {
                            printWriter.println("<hr><h2 align=center>Total cost: " + totalCost + "RUB");
                            printWriter.println("<form action='flowers' method='post'>" +
                                    "<input type=submit name='createOrder' value='Create Order' style='display:inline-block; margin-top:12; margin-bottom:0; margin-left:0; margin-right:0'/>" +
                                    "</form>");
                            if(req.getParameter("createOrder") != null) {
                                if(totalCost.compareTo(customer.getMoney()) == 1){
                                    printWriter.println("<h2 align=center>Impossible order! Sorry but you don't have enough money...</h2>");
                                }
                                else {
                                    printWriter.println("<h2 align=center>Your order is successfully completed!</h2>");
                                    orderBusinessService.insertOrder(mapService.mapToCustomerEntity(customer, new com.accenture.flowershop.be.entity.customer.Customer()),
                                            "open", new Date(System.currentTimeMillis()), null, customer.getDiscount(), totalCost);
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e){
            printWriter.println("<h1 align=center>Error on show flowers</h1>");
            printWriter.println(e.getMessage());
        }
        printWriter.println("</body></html>");
    }

    private void showMainFlowerInfo(PrintWriter printWriter, String name, String cost, String countOnStocks){
        printWriter.println("<hr>" +
                "<h3 align=center>Name: " + name + "</h3>" +
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
