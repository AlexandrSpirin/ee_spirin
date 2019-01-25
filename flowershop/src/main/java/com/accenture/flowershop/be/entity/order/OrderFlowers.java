package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.flower.Flower;

import javax.persistence.*;

@Entity
@Table(name="orders_flowers")
public class OrderFlowers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_flowers_seq")
    @SequenceGenerator(name = "order_flowers_seq", sequenceName = "order_flowers_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "flower_id")
    private Flower flower;

    private int flowerCount;


    public OrderFlowers() {}

    public OrderFlowers(Order order, Flower flower, int flowerCount) {
        this.order = order;
        this.flower = flower;
        this.flowerCount = flowerCount;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public int getFlowerCount() {
        return flowerCount;
    }

    public void setFlowerCount(int flowerCount) {
        this.flowerCount = flowerCount;
    }
}
