package com.palmble.service;

import java.util.List;

import com.palmble.entity.UserPermission;

public interface UserPermissionService {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPermission record);

    int insertSelective(UserPermission record);

    List<UserPermission> selectByGroupOrUserId(Integer id);

    int updateByPrimaryKeySelective(UserPermission record);

    int updateByPrimaryKey(UserPermission record);
    List<Integer> selectPrivilegeUrlByGroupOrUserId(Integer userid);
    
}
