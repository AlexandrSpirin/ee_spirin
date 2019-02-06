package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.customer.Customer;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="orders")
@XmlRootElement
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderFlowers> orderFlowersList;

    @Enumerated(EnumType.STRING)
    private OrderStatus  status;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="close_date")
    private Date closeDate;

    private Integer discount;

    @Column(name="final_price")
    private BigDecimal finalPrice;



    public Order() {}

    public Order(Customer customer, List<OrderFlowers> orderFlowersList, OrderStatus status, Date createDate, Date closeDate, Integer discount, BigDecimal finalPrice){
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

    @XmlElement
    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    @XmlElement
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderFlowers> getOrderFlowersList() {
        return orderFlowersList;
    }

    @XmlElement
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

    public OrderStatus getStatus() {
        return status;
    }

    @XmlElement
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    @XmlElement
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    @XmlElement
    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Integer getDiscount() {
        return discount;
    }

    @XmlElement
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    @XmlElement
    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }
}
