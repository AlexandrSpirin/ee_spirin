package com.accenture.flowershop.fe.dto.order;

import com.accenture.flowershop.fe.dto.customer.Customer;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Order {
    private Long id;

    private Customer customer;

    private List<OrderFlowers> orderFlowersList;

    private String status;

    private Date createDate;

    private Date closeDate;

    private Integer discount;

    private BigDecimal finalPrice;



    public Order() {}

    public Order(Customer customer, List<OrderFlowers> orderFlowersList, String status, Date createDate, Date closeDate, Integer discount, BigDecimal finalPrice){
        this.customer = customer;
        this.orderFlowersList = orderFlowersList;
        this.status = status;
        this.createDate = createDate;
        this.closeDate = closeDate;
        this.discount = discount;
        this.finalPrice = finalPrice;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderFlowers> getOrderFlowersList() {
        return orderFlowersList;
    }

    public void setOrderFlowersList(List<OrderFlowers> orderFlowersList) {
        this.orderFlowersList = orderFlowersList;
    }

    public  void addOrderFlowers(OrderFlowers oF){
        if(orderFlowersList == null){
            orderFlowersList = new ArrayList<>();
        }
        orderFlowersList.add(oF);
        oF.setOrder(this);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }
}
