package com.accenture.flowershop.fe.dto.flower;

import java.math.BigDecimal;
import java.util.Objects;

public class Flower {
    private Long id;

    private String name;

    private BigDecimal cost;


    public Flower(){}

    public Flower(Long id, String name, BigDecimal cost){
        this.id = id;
        this.name = name;
        this.cost = cost;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flower flower = (Flower) o;
        return id.equals(flower.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
