package com.xxxx.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.OrderDetailsMapper;
import com.xxxx.crm.query.OrderDetailsQuery;
import com.xxxx.crm.vo.OrderDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 */
@Service
public class OrderDetailsService extends BaseService<OrderDetails ,Integer> {

    @Resource
    private OrderDetailsMapper orderDetailsMapper;


    /***
     * 分页条件查询订单详情列表
     * @param orderDetailsQuery
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> queryOrderDetailsByParams(OrderDetailsQuery orderDetailsQuery) {
        Map<String, Object> map = new HashMap<>();

        // 开启分页
        PageHelper.startPage(orderDetailsQuery.getPage(), orderDetailsQuery.getLimit());
        // 得到对应分页对象
        PageInfo<OrderDetails> pageInfo = new PageInfo<>(orderDetailsMapper.selectByParams(orderDetailsQuery));

        // 设置map对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        // 设置分页好的列表
        map.put("data",pageInfo.getList());

        return map;
    }
}
