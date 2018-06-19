package com.palmble.service;

import java.util.List;

import com.palmble.entity.AdminUser;
import com.palmble.entity.AdminUserExample;

public interface AdminUserService {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
    
    List<AdminUser> selectByExample(AdminUserExample example);
}
