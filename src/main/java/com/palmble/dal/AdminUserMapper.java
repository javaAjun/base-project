package com.palmble.dal;

import org.apache.ibatis.annotations.Mapper;

import com.palmble.entity.AdminUser;

@Mapper
public interface AdminUserMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
}