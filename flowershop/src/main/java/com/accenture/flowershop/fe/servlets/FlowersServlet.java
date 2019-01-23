package com.accenture.flowershop.fe.servlets;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.flowerPool.FlowerPoolBusinessService;
import com.accenture.flowershop.be.entity.account.AccountType;
import com.accenture.flowershop.fe.dto.flower.Flower;
import com.accenture.flowershop.fe.dto.flowerPool.FlowerPool;
import com.accenture.flowershop.fe.ws.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Component
@WebServlet(name = "FlowersServlet",
        urlPatterns = {"/flowers"})
public class FlowersServlet extends HttpServlet {

    @Autowired
    FlowerBusinessService flowerBusinessService;

    @Autowired
    FlowerPoolBusinessService flowerPoolBusinessService;

    @Autowired
    MapService mapService;

    private HashMap<BigInteger, Integer> flowersOnStocks = new HashMap<>();

    private HashMap<BigInteger, Integer> flowersInBasket = new HashMap<>();

    private HttpSession session;

    private List<Flower> flowerDtos = new ArrayList();

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
        showFlowers(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
    {
        showFlowers(req,resp);
    }

    public void showFlowers(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        session = req.getSession();
        PrintWriter printWriter = resp.getWriter();

        String searchNameText = req.getParameter("searchNameText") == null ? "" : req.getParameter("searchNameText");
        BigDecimal searchMinCostText = req.getParameter("searchMinCostText") == null ? new BigDecimal(0) : new BigDecimal(req.getParameter("searchMinCostText"));
        BigDecimal searchMaxCostText = req.getParameter("searchMaxCostText") == null ? new BigDecimal(0) : new BigDecimal(req.getParameter("searchMaxCostText"));

        String login = session.getAttribute("login") == null ? "" : (String)session.getAttribute("login");
        String discount = session.getAttribute("discount") == null ? "" : (String)session.getAttribute("discount");
        AccountType userType = session.getAttribute("userType") == null ? null : (AccountType)session.getAttribute("userType");
        BigDecimal money = session.getAttribute("money") == null ? new BigDecimal(0) : (BigDecimal)session.getAttribute("money");
        flowersInBasket = session.getAttribute("flowersInBasket") == null ? new HashMap<BigInteger, Integer>() : (HashMap<BigInteger, Integer>) session.getAttribute("flowersInBasket");


        printWriter.println("<html><body>");

        try {
            if(discount==""||login==""||userType==null) {
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
                        "<p>" +
                        (userType == AccountType.ADMIN ?
                                        "<form action='registration.jsp' style='display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'>" +
                                        "<input type='submit' name = 'registrationButton' value='Registration'>" +
                                        "</form>"
                                : ("You have money: " + money + "RUB and discount: " + discount + "%" +
                                "<form action='flowers' method='post' style='display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'>" +
                                "<input type=submit name='basketButton' value='Basket'/>" +
                                "</form>" )) +
                        "</h2>" +
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
                mapService.mapAllFlowerDtos(flowerDtos, flowerBusinessService.findFlowers(searchNameText, searchMinCostText, searchMaxCostText));
                List<FlowerPool> flowerPoolDtos = new ArrayList();
                for(int i=0; i < flowerDtos.size(); i++) {
                    List<FlowerPool> flowerPoolDtosWithFlowerID = new ArrayList();
                    mapService.mapAllFlowerPoolDtos(flowerPoolDtosWithFlowerID, flowerPoolBusinessService.findFlowerPoolsByFlowerId(flowerDtos.get(i).getId()));
                    for (FlowerPool fP:flowerPoolDtosWithFlowerID) {
                        flowerPoolDtos.add(fP);
                    }
                }

                if (flowerDtos.isEmpty()) {
                    printWriter.println("<h1 align=center>No flower found with these parameters!</h1>");
                } else {
                    if(userType != AccountType.ADMIN) {
                        addFlowersToBasket(req, resp);
                    }
                    flowersOnStocks.clear();
                    for (Flower f : flowerDtos) {
                        printWriter.println("<hr>" +
                                "<h3 align=center>Name: " + f.getName() + "</h3>" +
                                "<h4 align=center>Cost: " + f.getCost() + "RUB</h4>");
                        int flowerCountOnStocks = 0;
                        for (FlowerPool fP: flowerPoolDtos) {
                            if(fP.getFlowerId()==f.getId()){
                                flowerCountOnStocks += fP.getFlowerCount();
                            }
                        }
                        flowersOnStocks.put(BigInteger.valueOf(f.getId()),flowerCountOnStocks);

                        printWriter.println("<h4 align=center>Count on stocks: " + flowerCountOnStocks);

                        if(userType != AccountType.ADMIN) {
                            int flowerCountInBasket = 0;
                            for (HashMap.Entry<BigInteger, Integer> entry : flowersInBasket.entrySet()) {
                                if (entry.getKey().toString().equals(String.valueOf(f.getId()))) {
                                    flowerCountInBasket = entry.getValue() == null ? 0 : entry.getValue();
                                }
                            }
                            String flowerCountForAddingToBasket = req.getParameter("flower" + f.getId() + "CountInBasket") == null ? "0" : req.getParameter("flower" + f.getId() + "CountInBasket");
                            printWriter.println(" (" + flowerCountInBasket + " in Your Basket)" +
                                    "<form action='flowers' method='post'>" +
                                    "<input type=text name='flower" + f.getId() + "CountForAddingToBasket' onkeypress='return event.charCode >= 48 " +
                                    "&& event.charCode <= 57'  value='" + flowerCountForAddingToBasket +
                                    "' style='display:inline-block; margin-top:12; margin-bottom:0; margin-left:0; margin-right:0'/>" +
                                    "<input type=submit name='addFlower" + f.getId() + "ToBasket' value='Add To Basket' style='display:inline-block; margin-top:12; margin-bottom:0; margin-left:0; margin-right:0'/>" +
                                    "</form>");
                        }
                        printWriter.println("</h4>");
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

    public void addFlowersToBasket(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        try {
            PrintWriter printWriter = resp.getWriter();
            for (Flower f : flowerDtos) {
                if (req.getParameter("addFlower" + f.getId() + "ToBasket") != null) {
                    for (HashMap.Entry<BigInteger, Integer> entry : flowersInBasket.entrySet()) {
                        if (entry.getKey().toString().equals(String.valueOf(f.getId()))) {
                            Integer flowerCountInBasket = entry.getValue() + Integer.parseInt(req.getParameter("flower" + f.getId() + "CountForAddingToBasket"));
                            Integer flowerCountOnStocks = flowersOnStocks.get(entry.getKey()) == null ? 0 : flowersOnStocks.get(entry.getKey());
                            if (flowerCountInBasket > flowerCountOnStocks) {
                                flowerCountInBasket = flowerCountOnStocks;
                            }
                            req.setAttribute("flower" + f.getId() + "CountForAddingToBasket", "0");
                            entry.setValue(flowerCountInBasket);
                            session.setAttribute("flowersInBasket", flowersInBasket);
                        }
                    }
                }
            }
        }
        catch (Exception e){

        }
    }
}
