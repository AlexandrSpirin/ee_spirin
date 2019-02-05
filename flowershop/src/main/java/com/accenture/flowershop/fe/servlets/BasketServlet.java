package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.flowerStock.FlowerStockBusinessService;
import com.accenture.flowershop.be.entity.account.AccountType;
import com.accenture.flowershop.fe.dto.customer.Customer;
import com.accenture.flowershop.fe.dto.flower.Flower;
import com.accenture.flowershop.fe.dto.order.Order;
import com.accenture.flowershop.fe.dto.order.OrderFlowers;
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
import java.math.RoundingMode;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;


@Component
@WebServlet(name = "BasketServlet",
        urlPatterns = {"/basket"})
public class BasketServlet extends HttpServlet {

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


    /**
     * Отображение основной информации страницы
     *
     * @param req запрос, сессия и параметры которого используются в работе
     * @param resp ответ, printWriter которого используется в работе
     * @throws IOException исключение ввода-вывода, которое будет обрабатываться
     */
    private void showBasket(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Сессия, атрибуты которой используются для работы
        HttpSession session = req.getSession();
        //Объект для печати объектов в поток вывода текста(отображение на странице)
        PrintWriter printWriter = resp.getWriter();

        //Тип аккаунта пользователя
        AccountType userType = session.getAttribute(SessionAttribute.USER_TYPE.toString()) == null ? null : (AccountType) session.getAttribute(SessionAttribute.USER_TYPE.toString());
        //Логин пользователя
        String login = session.getAttribute(SessionAttribute.LOGIN.toString()) == null ? "" : (String) session.getAttribute(SessionAttribute.LOGIN.toString());

        //Покупатель, которому принадлежит эта корзина
        Customer customer = null;
        if(session.getAttribute(SessionAttribute.CUSTOMER.toString()) != null) {
            customer = (com.accenture.flowershop.fe.dto.customer.Customer) session.getAttribute(SessionAttribute.CUSTOMER.toString());
        }

        printWriter.println("<html><body>");

        try {
            //Проверяем атрибуты сессии на корректность
            if (login.equals("") || userType != AccountType.CUSTOMER || customer == null) {
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


                //Текст, используемый в поиске цветка по имени
                String searchNameText = req.getParameter("searchNameText") == null ? "" : req.getParameter("searchNameText");
                //Минимальная цена, используемая в поиске цветка
                BigDecimal searchMinCostText = req.getParameter("searchMinCostText") == null ? new BigDecimal(0) : new BigDecimal(req.getParameter("searchMinCostText"));
                //Максимальная цена, используемая в поиске цветка
                BigDecimal searchMaxCostText = req.getParameter("searchMaxCostText") == null ? new BigDecimal(0) : new BigDecimal(req.getParameter("searchMaxCostText"));

                showUserPanel(printWriter, login, customer, searchNameText, searchMinCostText.toString(), searchMaxCostText.toString());

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
                        //Заказ, который может быть создан из цветков, находящихся в корзине
                        Order order = new Order();

                        //Итоговая цена заказа
                        BigDecimal finalPrice = BigDecimal.ZERO;

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
                                //Цена цветка этого типа с учетом скидки
                                BigDecimal flowerCostWithDiscount = entry.getKey().getCost().multiply(BigDecimal.valueOf(1 - (customer.getDiscount() * 0.01))).setScale(2, RoundingMode.CEILING);
                                //Цена за все цветки этого типа в корзине пользователя с учетом скидки
                                BigDecimal totalCostForCurrentFlowerType = flowerCostWithDiscount.multiply(new BigDecimal(entry.getValue())).setScale(2, RoundingMode.CEILING);

                                finalPrice = finalPrice.add(totalCostForCurrentFlowerType);

                                //Цветки заказа из цветков, находящихся в корзине
                                OrderFlowers orderFlowers = new OrderFlowers();
                                orderFlowers.setFlower(entry.getKey());
                                orderFlowers.setFlowerCount(entry.getValue());

                                order.addOrderFlowers(orderFlowers);

                                //Показываем всю информацию о цветке этого типа
                                printWriter.println("<hr>");
                                showMainFlowerInfo(printWriter, entry.getKey().getName(), flowerCostWithDiscount.toString(), flowerCountOnStock.toString());
                                showCustomerFlowerInfo(printWriter, entry.getKey().getId().toString(), entry.getValue().toString(), totalCostForCurrentFlowerType.toString(), flowerCountForChangeBasket.toString());
                            }
                        }

                        printWriter.println("<hr><h2 align=center>Total cost: " + finalPrice + "RUB" +
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
                            order.setFinalPrice(finalPrice);

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


    /**
     * Отображение основной панели покупателя
     *
     * @param printWriter объект для печати объектов в поток вывода текста(отображение на странице)
     * @param login логин пользователя, который указывается в приветствии
     * @param customer покупатель, у которого для отображения берется количество денег и скидка
     * @param searchNameText текст, используемый в поиске цветка по имени
     * @param searchMinCostText минимальная цена, используемая в поиске цветка
     * @param searchMaxCostText максимальная цена, используемая в поиске цветка
     */
    private void showUserPanel(PrintWriter printWriter, String login, Customer customer, String searchNameText, String searchMinCostText, String searchMaxCostText){
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
    }

    /**
     * Отображение основной информации об указанном цветке
     *
     * @param printWriter бъект для печати объектов в поток вывода текста(отображение на странице)
     * @param name название цветка
     * @param cost цена цветка этого типа за штуку
     * @param countOnStocks количество цветов этого типа на складе
     */
    private void showMainFlowerInfo(PrintWriter printWriter, String name, String cost, String countOnStocks){
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
