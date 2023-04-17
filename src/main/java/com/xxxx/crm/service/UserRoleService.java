package com.xxxx.crm.service;

import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.UserRoleMapper;
import com.xxxx.crm.vo.UserRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 */
@Service
public class UserRoleService extends BaseService<UserRole, Integer> {

    @Resource
    private UserRoleMapper userRoleMapper;
}
