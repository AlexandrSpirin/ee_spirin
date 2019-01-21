package com.accenture.flowershop.be.entity.order;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashMap;


@Entity(name="orders")
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
    private long id;

    private HashMap<BigInteger, Integer> flowers;

    private Integer discount;



    public Order() {}

    public Order(HashMap<BigInteger, Integer> flowers, Integer discount){
        this.flowers = flowers;
        this.discount = discount;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HashMap<BigInteger, Integer> getFlowers() {
        return flowers;
    }

    public void setFlowers(HashMap<BigInteger, Integer> flowers) {
        this.flowers = flowers;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
