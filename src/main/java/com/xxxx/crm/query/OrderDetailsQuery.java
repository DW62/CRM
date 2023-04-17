package com.xxxx.crm.query;

import com.xxxx.crm.base.BaseQuery;

/**
 */
public class OrderDetailsQuery extends BaseQuery {

    private Integer orderId; // 订单ID

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
