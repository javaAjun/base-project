package com.palmble.dal;

import java.util.List;
import java.util.Map;

import com.palmble.entity.AdminUser;

public interface AdminUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
    List<AdminUser> selectBySelective(Map<String,Object> map);
}