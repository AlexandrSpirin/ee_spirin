package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.flower.Flower;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="order_flowers")
@XmlRootElement
public class OrderFlowers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_flowers_seq")
    @SequenceGenerator(name = "order_flowers_seq", sequenceName = "order_flowers_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "flower_id")
    private Flower flower;

    @Column(name = "flower_count")
    private Integer flowerCount;


    public OrderFlowers() {}

    public OrderFlowers(Order order, Flower flower, Integer flowerCount) {
        this.order = order;
        this.flower = flower;
        this.flowerCount = flowerCount;
    }


    public Long getId() {
        return id;
    }

    @XmlElement
    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    @XmlElement
    public void setOrder(Order order) {
        this.order = order;
    }

    public Flower getFlower() {
        return flower;
    }

    @XmlElement
    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public Integer getFlowerCount() {
        return flowerCount;
    }

    @XmlElement
    public void setFlowerCount(Integer flowerCount) {
        this.flowerCount = flowerCount;
    }
}
