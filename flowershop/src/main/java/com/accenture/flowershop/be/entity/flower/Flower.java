package com.accenture.flowershop.be.entity.flower;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;


@Entity
@Table(name="flowers")
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flower_seq")
    @SequenceGenerator(name = "flower_seq", sequenceName = "flower_seq", allocationSize = 1)
    private Long id;

    private String name;

    private BigDecimal cost;


    public Flower(){}

    public Flower(String name, BigDecimal cost){
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
