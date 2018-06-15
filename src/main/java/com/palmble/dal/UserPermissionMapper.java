package com.palmble.dal;

import org.apache.ibatis.annotations.Mapper;

import com.palmble.entity.UserPermission;

@Mapper
public interface UserPermissionMapper extends MyMapper<UserPermissionMapper>{
    int deleteByPrimaryKey(Integer id);

    int insert(UserPermission record);

    int insertSelective(UserPermission record);

    UserPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPermission record);

    int updateByPrimaryKey(UserPermission record);
    
}