package com.accenture.flowershop.fe.dto.order;


import com.accenture.flowershop.fe.dto.flower.Flower;

public class OrderFlowers {
    private Long id;

    private Order order;

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
