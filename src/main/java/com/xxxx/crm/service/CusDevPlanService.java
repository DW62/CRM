package com.xxxx.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.CusDevPlanMapper;
import com.xxxx.crm.dao.SaleChanceMapper;
import com.xxxx.crm.query.CusDevPlanQuery;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.CusDevPlan;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 */
@Service
public class CusDevPlanService extends BaseService<CusDevPlan, Integer> {

    @Resource
    private CusDevPlanMapper cusDevPlanMapper;
    @Resource
    private SaleChanceMapper saleChanceMapper;


    /**
     * 多条件分页查询客户开发计划 （返回的数据格式必须满足LayUi中数据表格要求的格式）
     * @param cusDevPlanQuery
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> queryCUsDevPlanByParams(CusDevPlanQuery cusDevPlanQuery) {

        Map<String, Object> map = new HashMap<>();

        // 开启分页
        PageHelper.startPage(cusDevPlanQuery.getPage(), cusDevPlanQuery.getLimit());
        // 得到对应分页对象
        PageInfo<CusDevPlan> pageInfo = new PageInfo<>(cusDevPlanMapper.selectByParams(cusDevPlanQuery));

        // 设置map对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        // 设置分页好的列表
        map.put("data",pageInfo.getList());

        return map;
    }


    /***
     * 添加客户开发计划项数据
     *  1. 参数校验
     *      营销机会ID   非空，数据存在
     *      计划项内容   非空
     *      计划时间     非空
     *  2. 设置参数的默认值
     *      是否有效    默认有效
     *      创建时间    系统当前时间
     *      修改时间    系统当前时间
     *  3. 执行添加操作，判断受影响的行数
     *
     * @param cusDevPlan
     * @return void
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCusDevPlan(CusDevPlan cusDevPlan) {
        /* 1. 参数校验  */
        checkCusDevPlanParams(cusDevPlan);

        /* 2. 设置参数的默认值 */
        // 是否有效    默认有效
        cusDevPlan.setIsValid(1);
        // 创建时间    系统当前时间
        cusDevPlan.setCreateDate(new Date());
        // 修改时间    系统当前时间
        cusDevPlan.setUpdateDate(new Date());

        /* 3. 执行添加操作，判断受影响的行数 */
        AssertUtil.isTrue(cusDevPlanMapper.insertSelective(cusDevPlan) != 1, "计划项数据添加失败！");
    }

    /**
     * 更新客户开发计划项数据
     *  1. 参数校验
     *      计划项ID     非空，数据存在
     *      营销机会ID   非空，数据存在
     *      计划项内容   非空
     *      计划时间     非空
     *  2. 设置参数的默认值
     *      修改时间    系统当前时间
     *  3. 执行更新操作，判断受影响的行数
     *
     * @param cusDevPlan
     * @return void
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCusDevPlan(CusDevPlan cusDevPlan){
        /* 1. 参数校验  */
        // 计划项ID     非空，数据存在
        AssertUtil.isTrue(null == cusDevPlan.getId()
                || cusDevPlanMapper.selectByPrimaryKey(cusDevPlan.getId()) == null, "数据异常，请重试！");
        checkCusDevPlanParams(cusDevPlan);

        /* 2. 设置参数的默认值 */
        // 修改时间    系统当前时间
        cusDevPlan.setUpdateDate(new Date());

        /* 3. 执行更新操作，判断受影响的行数 */
        AssertUtil.isTrue(cusDevPlanMapper.updateByPrimaryKeySelective(cusDevPlan) != 1, "计划项更新失败！");

    }

    /**
     *  参数校验
     *      营销机会ID   非空，数据存在
     *      计划项内容   非空
     *      计划时间     非空
     * @param cusDevPlan
     * @return void
     */
    private void checkCusDevPlanParams(CusDevPlan cusDevPlan) {
        // 营销机会ID   非空，数据存在
        Integer sId = cusDevPlan.getSaleChanceId();
        AssertUtil.isTrue(null == sId || saleChanceMapper.selectByPrimaryKey(sId) == null,"数据异常，请重试！");

        // 计划项内容   非空
        AssertUtil.isTrue(StringUtils.isBlank(cusDevPlan.getPlanItem()), "计划项内容不能为空！");

        // 计划时间     非空
        AssertUtil.isTrue(null == cusDevPlan.getPlanDate(), "计划时间不能为空！");

    }


    /***
     * 删除计划项
     *  1. 判断ID是否为空，且数据存在
     *  2. 修改isValid属性
     *  3. 执行更新操作
     * @param id
     * @return void
     */
    public void deleteCusDevPlan(Integer id) {
        // 1. 判断ID是否为空，且数据存在
        AssertUtil.isTrue(null == id, "待删除记录不存在！");
        // 通过ID查询计划项对象
        CusDevPlan cusDevPlan = cusDevPlanMapper.selectByPrimaryKey(id);
        // 设置记录无效（删除）
        cusDevPlan.setIsValid(0);
        cusDevPlan.setUpdateDate(new Date());
        // 执行更新操作
        AssertUtil.isTrue(cusDevPlanMapper.updateByPrimaryKeySelective(cusDevPlan) != 1, "计划项数据删除失败！");
    }
}
