package com.accenture.flowershop.fe.dto.flowerStock;

import com.accenture.flowershop.fe.dto.flower.Flower;


public class FlowerStock {
    private Long id;

    private Flower flower;

    private Integer flowerCount;



    public FlowerStock() {}

    public FlowerStock(Flower flower, Integer flowerCount) {
        this.flower = flower;
        this.flowerCount = flowerCount;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public Integer getFlowerCount() {
        return flowerCount;
    }

    public void setFlowerCount(Integer flowerCount) {
        this.flowerCount = flowerCount;
    }
}