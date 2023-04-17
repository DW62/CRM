package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.CustomerOrder;

import java.util.Map;

public interface CustomerOrderMapper extends BaseMapper<CustomerOrder, Integer> {

    // 通过订单ID查询订单记录
    Map<String ,Object> queryOrderById(Integer orderId);

    // 通过客户ID查询最后一条订单记录
    CustomerOrder queryLossCustomerOrderByCustomerId(Integer customerId);
}