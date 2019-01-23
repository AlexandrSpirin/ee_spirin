package com.accenture.flowershop.be.entity.flowerPool;

import javax.persistence.*;


@Entity(name="flower_pools")
@Table(name="flower_pools")
@IdClass(FlowerPoolId.class)
public class FlowerPool {
    @Id
    private long id;
    @Id
    @Column(name = "flower_id")
    private long flowerId;

    @Column(name = "flower_count")
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
