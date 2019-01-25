package com.accenture.flowershop.be.entity.flowerStock;

import com.accenture.flowershop.be.entity.flower.Flower;

import javax.persistence.*;


@Entity
@Table(name="flower_stocks")
public class FlowerStock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flower_stock_seq")
    @SequenceGenerator(name = "flower_stock_seq", sequenceName = "flower_stock_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "flower_id")
    private Flower flower;

    @Column(name = "flower_count")
    private int flowerCount;

    @Column(name = "flower_stock_id")
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
