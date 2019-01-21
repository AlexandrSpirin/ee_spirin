package com.accenture.flowershop.fe.dto.order;

import java.math.BigInteger;
import java.util.HashMap;

public class Order {
    private long id;

    private HashMap<BigInteger, Integer> flowers;

    private Integer discount;



    public Order() {}

    public Order(long id, HashMap<BigInteger, Integer> flowers, Integer discount){
        this.id = id;
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
