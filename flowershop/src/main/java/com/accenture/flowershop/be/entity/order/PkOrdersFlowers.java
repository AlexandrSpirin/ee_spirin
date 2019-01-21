package com.accenture.flowershop.be.entity.order;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class PkOrdersFlowers implements Serializable {
    private long orderId;

    private long flowerId;


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(long flowerId) {
        this.flowerId = flowerId;
    }
}
