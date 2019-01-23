package com.accenture.flowershop.fe.dto.flowerPool;

public class FlowerPool {
    private long id;

    private long flowerId;

    private int flowerCount;



    public FlowerPool() {}

    public FlowerPool(long id, long flowerId, int flowerCount) {
        this.id = id;
        this.flowerId = flowerId;
        this.flowerCount = flowerCount;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(long flowerId) {
        this.flowerId = flowerId;
    }

    public int getFlowerCount() {
        return flowerCount;
    }

    public void setFlowerCount(int flowerCount) {
        this.flowerCount = flowerCount;
    }
}
