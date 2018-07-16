package com.palmble.dal;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.palmble.entity.UserPermission;

@Mapper
public interface UserPermissionMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(UserPermission record);

    int insertSelective(UserPermission record);

    List<UserPermission> selectByGroupOrUserId(Integer id);

    int updateByPrimaryKeySelective(UserPermission record);

    int updateByPrimaryKey(UserPermission record);
    
    List<Integer> selectPrivilegeUrlByGroupOrUserId(Integer userid);
    
    
}