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
        showMainInfo(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        showMainInfo(req, resp);
    }




    /**
     * Отображение основной информации страницы
     *
     * @param req запрос, сессия и параметры которого используются в работе
     * @param resp ответ, printWriter которого используется в работе
     * @throws IOException исключение ввода-вывода, которое будет обрабатываться
     */
    private void showMainInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Сессия, атрибуты которой используются для работы
        HttpSession session = req.getSession();
        //Объект для печати объектов в поток вывода текста(отображение на странице)
        PrintWriter printWriter = resp.getWriter();

        //Тип аккаунта пользователя
        AccountType userType = session.getAttribute(SessionAttribute.USER_TYPE.toString()) == null ? null : (AccountType) session.getAttribute(SessionAttribute.USER_TYPE.toString());
        //Логин пользователя
        String login = session.getAttribute(SessionAttribute.LOGIN.toString()) == null ? "" : (String) session.getAttribute(SessionAttribute.LOGIN.toString());

        //Покупатель, просматривающий свои заказы
        Customer customer = null;
        if (session.getAttribute(SessionAttribute.CUSTOMER.toString()) != null) {
            customer = (com.accenture.flowershop.fe.dto.customer.Customer) session.getAttribute(SessionAttribute.CUSTOMER.toString());
        }

        printWriter.println("<html><body>");

        try {
            if (login.equals("") || userType == null || (userType == AccountType.CUSTOMER && customer == null)) {
                printWriter.println("Login error! Please login again!" +
                        "<form action='index.jsp' style='display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'>" +
                        "<input type=submit name='toMainPage' value='Main Page'/>" +
                        "</form>");
            } else {
                //Проверяем нажатие на кнопку перехода от всего ассортимента цветов к корзине
                if (req.getParameter("basketButton") != null) {
                    resp.sendRedirect("basket");
                }

                //Проверяем нажатие на кнопку перехода от всего ассортимента цветов к заказам
                if (req.getParameter("ordersButton") != null) {
                    resp.sendRedirect("orders");
                }

                //Мапа для корзины пользователя, в которой хранятся цветки(DTO)/их количество
                HashMap<Flower, Integer> flowersInBasket = session.getAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString()) == null ?
                        new HashMap<Flower, Integer>() : (HashMap<Flower, Integer>) session.getAttribute(SessionAttribute.FLOWERS_IN_BASKET.toString());

                //Текст, используемый в поиске цветка по имени
                String searchNameText = req.getParameter("searchNameText") == null ? "" : req.getParameter("searchNameText");
                //Минимальная цена, используемая в поиске цветка
                BigDecimal searchMinCostText = req.getParameter("searchMinCostText") == null ? new BigDecimal(0) : new BigDecimal(req.getParameter("searchMinCostText"));
                //Максимальная цена, используемая в поиске цветка
                BigDecimal searchMaxCostText = req.getParameter("searchMaxCostText") == null ? new BigDecimal(0) : new BigDecimal(req.getParameter("searchMaxCostText"));

                //Показываем основную панель пользователя
                showUserPanel(printWriter, login, userType, customer, searchNameText, searchMinCostText.toString(), searchMaxCostText.toString());

                //Все типы цветов, подходящие под условия поиска
                List<Flower> flowerDtos = new ArrayList();
                for (com.accenture.flowershop.be.entity.flower.Flower f: flowerBusinessService.findFlowers(searchNameText, searchMinCostText, searchMaxCostText)) {
                    flowerDtos.add(mapper.map(f, Flower.class));
                }

                //Все позиции склада с цветами, типы которых подходят под условия поиска
                List<FlowerStock> flowerStockDtos = new ArrayList();
                for (int i = 0; i < flowerDtos.size(); i++) {
                    for(com.accenture.flowershop.be.entity.flowerStock.FlowerStock fS : flowerStockBusinessService.findFlowerStocksByFlower(mapper.map(flowerDtos.get(i), com.accenture.flowershop.be.entity.flower.Flower.class))){
                        flowerStockDtos.add(mapper.map(fS, FlowerStock.class));
                    }
                }

                if (flowerStockDtos.isEmpty()) {
                    printWriter.println("<hr><h2 align = center>No flower found with these parameters!</h2>");
                } else {
                    //Для админа показываем только основную информацию о всех цветках, подходящих по условиям поиска
                    if (userType == AccountType.ADMIN) {
                        for (FlowerStock fS : flowerStockDtos) {
                            printWriter.println("<hr>");
                            showMainFlowerInfo(printWriter, fS.getFlower().getName(), fS.getFlower().getCost().toString(), fS.getFlowerCount().toString());
                        }
                    //Для покупателя показываем основную информацию и информацию покупателя о цветках, подходящих по условиям поиска
                    } else if (userType == AccountType.CUSTOMER) {
                        for (FlowerStock fS : flowerStockDtos) {
                            //Текущее количество цветов этого типа в корзине
                            Integer flowerCountInBasket = 0;
                            if (flowersInBasket.get(fS.getFlower()) != null) {
                                flowerCountInBasket = flowersInBasket.get(fS.getFlower());
                            }

                            //Количество цветов этого типа, которое должно стать в корзине
                            Integer newFlowerCountInBasket = flowerCountInBasket;

                            //Количество цветов этого типа, на которое покупатель хотел изменить количество цветов этого типа в своей корзине
                            Integer flowerCountForChangeBasket = req.getParameter("flower" + fS.getFlower().getId() + "CountForChangeBasket") == null ? 0 : Integer.valueOf(req.getParameter("flower" + fS.getFlower().getId() + "CountForChangeBasket"));

                            //Проверяем нажатие на кнопки для изменения количества цветков в корзине, только если количество, на которое необходимо изменить количество цветков в корзине больше 0
                            if(flowerCountForChangeBasket > 0) {
                                //Если нажали кнопку добавить цветы в корзину для цветов этого типа
                                if (req.getParameter("addFlower" + fS.getFlower().getId() + "ToBasket") != null) {
                                    if (flowerCountInBasket < fS.getFlowerCount()) {
                                        newFlowerCountInBasket += flowerCountForChangeBasket;
                                        if (newFlowerCountInBasket > fS.getFlowerCount()) {
                                            newFlowerCountInBasket = fS.getFlowerCount();
                                        }
                                        flowersInBasket.put(fS.getFlower(), newFlowerCountInBasket);

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

                            //Цена цветка этого типа с учетом скидки
                            BigDecimal flowerCostWithDiscount = fS.getFlower().getCost().multiply(BigDecimal.valueOf(1 - (customer.getDiscount() * 0.01))).setScale(2, RoundingMode.CEILING);
                            //Цена за все цветки этого типа в корзине пользователя с учетом скидки
                            BigDecimal totalCostForCurrentFlowerType = flowerCostWithDiscount.multiply(new BigDecimal(newFlowerCountInBasket)).setScale(2, RoundingMode.CEILING);

                            //Показываем всю информацию о цветке этого типа
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




    /**
     * Отображение основной информации об указанном цветке
     *
     * @param printWriter бъект для печати объектов в поток вывода текста(отображение на странице)
     * @param name название цветка
     * @param cost цена цветка этого типа за штуку
     * @param countOnStocks количество цветов этого типа на складе
     */
    private void showMainFlowerInfo(PrintWriter printWriter, String name, String cost, String countOnStocks) {
        printWriter.println("<h3 align=center>Name: " + name + "</h3>" +
                "<h4 align=center>Cost: " + cost + "RUB</h4>" +
                "<h4 align=center>Count on stocks: " + countOnStocks);
    }


    /**
     * Отображение информации покупателя об указанном цветке
     *
     * @param printWriter объект для печати объектов в поток вывода текста(отображение на странице)
     * @param flowerId id цветка этого типа для создания элементов ввода(кнопки ввода добавить/убрать цветы в/из корзины,
     *                 поле ввода для количества цветков, на которое необходимо изменить количество цветков этого типа в корзине) для покупателя
     * @param flowerCountInBasket текущее количество цветков этого типа в корзине покупателя
     * @param totalCostForCurrentFlowerType итоговая цена за все цветки этого типа, находящиеся в корзине
     * @param flowerCountForChangeBasket количество цветков, на которое необходимо изменить количество цветков этого типа в корзине
     */
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


    /**
     * Отображение основной панели пользователя
     *
     * @param printWriter объект для печати объектов в поток вывода текста(отображение на странице)
     * @param login логин пользователя, который указывается в приветствии
     * @param userType тип аккаунта пользователя, от которого зависит отображение некоторых элементов
     * @param customer покупатель, у которого для отображения берется количество денег и скидка
     * @param searchNameText текст, используемый в поиске цветка по имени
     * @param searchMinCostText минимальная цена, используемая в поиске цветка
     * @param searchMaxCostText максимальная цена, используемая в поиске цветка
     */
    private  void  showUserPanel(PrintWriter printWriter, String login, AccountType userType, Customer customer, String searchNameText, String searchMinCostText, String searchMaxCostText){
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
    }
}
