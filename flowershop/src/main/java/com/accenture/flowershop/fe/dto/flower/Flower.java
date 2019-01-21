package com.accenture.flowershop.fe.dto.flower;

import java.math.BigDecimal;

public class Flower {
    private long id;

    private String name;

    private BigDecimal cost;


    public Flower(){}

    public Flower(long id, String name, BigDecimal cost){
        this.id = id;
        this.name = name;
        this.cost = cost;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
