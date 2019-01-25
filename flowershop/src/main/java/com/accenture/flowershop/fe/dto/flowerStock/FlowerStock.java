package com.accenture.flowershop.fe.dto.flowerStock;

import com.accenture.flowershop.fe.dto.flower.Flower;

public class FlowerStock {
    private Long id;

    private Flower flower;

    private int flowerCount;

    private Long flowerStockId;



    public FlowerStock() {}

    public FlowerStock(Long id, Flower flower, int flowerCount, Long flowerStockId) {
        this.id = id;
        this.flower = flower;
        this.flowerCount = flowerCount;
        this.flowerStockId = flowerStockId;
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

    public int getFlowerCount() {
        return flowerCount;
    }

    public void setFlowerCount(int flowerCount) {
        this.flowerCount = flowerCount;
    }

    public Long getFlowerStockId() {
        return flowerStockId;
    }

    public void setFlowerStockId(Long flowerStockId) {
        this.flowerStockId = flowerStockId;
    }
}
