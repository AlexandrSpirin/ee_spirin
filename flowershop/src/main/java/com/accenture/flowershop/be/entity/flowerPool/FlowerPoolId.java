package com.accenture.flowershop.be.entity.flowerPool;

import java.io.Serializable;

public class FlowerPoolId implements Serializable {
    private long id;

    private long flowerId;


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
}
