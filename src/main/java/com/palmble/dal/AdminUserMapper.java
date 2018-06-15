package com.palmble.dal;

import com.palmble.entity.AdminUser;

import tk.mybatis.mapper.common.Mapper;

public interface AdminUserMapper extends Mapper<AdminUserMapper>{
    int deleteByPrimaryKey(Integer id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
    
}