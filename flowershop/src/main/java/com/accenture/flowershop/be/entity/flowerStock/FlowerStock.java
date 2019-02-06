package com.accenture.flowershop.be.entity.flowerStock;

import com.accenture.flowershop.be.entity.flower.Flower;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="flower_stocks")
@XmlRootElement
public class FlowerStock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flower_stock_seq")
    @SequenceGenerator(name = "flower_stock_seq", sequenceName = "flower_stock_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "flower_id")
    private Flower flower;

    @Column(name = "flower_count")
    private Integer flowerCount;


    public FlowerStock() {}

    public FlowerStock(Flower flower, Integer flowerCount) {
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