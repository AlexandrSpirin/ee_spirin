package com.accenture.flowershop.be.entity.order;

import javax.persistence.*;

@Entity(name="orders_flowers")
@Table(name="orders_flowers")
public class OrderFlowers {
    @EmbeddedId
    private PkOrdersFlowers pkOrdersFlowers;

    private int flowerCount;


    public OrderFlowers() {}

    public OrderFlowers(PkOrdersFlowers pkOrdersFlowers, int flowerCount) {
        this.pkOrdersFlowers = pkOrdersFlowers;
        this.flowerCount = flowerCount;
    }


    public PkOrdersFlowers getPkOrdersFlowers() {
        return pkOrdersFlowers;
    }

    public void setPkOrdersFlowers(PkOrdersFlowers pkOrdersFlowers) {
        this.pkOrdersFlowers = pkOrdersFlowers;
    }

    public int getFlowerCount() {
        return flowerCount;
    }

    public void setFlowerCount(int flowerCount) {
        this.flowerCount = flowerCount;
    }
}
