package com.accenture.flowershop.fe.servlets;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.fe.dto.flower.Flower;
import com.accenture.flowershop.fe.ws.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@Component
@WebServlet(name = "FlowersServlet",
        urlPatterns = {"/flowers"})
public class FlowersServlet extends HttpServlet {

    @Autowired
    FlowerBusinessService flowerBusinessService;

    @Autowired
    MapService mapService;

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
        PrintWriter printWriter = resp.getWriter();

        printWriter.println("<html>");
        printWriter.println("<body>");

        try {
            printWriter.println("<hr>");
            printWriter.println("<h1 align=center>FLOWERS</h1>");
            printWriter.println("<hr>");
            List<Flower> flowerDtos = new ArrayList();
            mapService.mapAllFlowerDtos(flowerDtos, flowerBusinessService.getAllFlowers());
            for (Flower f:flowerDtos) {
                printWriter.println("<hr>");
                printWriter.println("<h1 align=center>Name: " + f.getName() + "</h1>");
                printWriter.println("<h1 align=center>Cost: " + f.getCost() + "</h1>");
            }
        }
        catch (Exception e){
                printWriter.println("<h1 align=center>Error on show flowers</h1>");
        }

        printWriter.println("</body>");
        printWriter.println("</html>");
    }
}
