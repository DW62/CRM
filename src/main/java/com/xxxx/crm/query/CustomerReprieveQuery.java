package com.xxxx.crm.query;

import com.xxxx.crm.base.BaseQuery;

/**
 */
public class CustomerReprieveQuery extends BaseQuery {

    // 流失客户ID
    private Integer lossId;

    public Integer getLossId() {
        return lossId;
    }

    public void setLossId(Integer lossId) {
        this.lossId = lossId;
    }
}
