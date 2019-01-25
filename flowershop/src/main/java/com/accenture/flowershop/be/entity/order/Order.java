package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.customer.Customer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;


@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany
    @JoinColumn(name = "order_flowers_id")
    private List<OrderFlowers> orderFlowers;

    private String status;

    private Date createDate;

    private Date closeDate;

    private Integer discount;

    private BigDecimal finalPrice;



    public Order() {}

    public Order(Customer customer, String status, Date createDate, Date closeDate, Integer discount, BigDecimal finalPrice){
        this.customer = customer;
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
