package com.accenture.flowershop.fe.dto.order;


public class OrderFlowers {
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
