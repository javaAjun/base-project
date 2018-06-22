package com.palmble.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmble.dal.UserPermissionMapper;
import com.palmble.entity.UserPermission;
import com.palmble.service.UserPermissionService;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {
	@Autowired
	private UserPermissionMapper userPermissionMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userPermissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserPermission record) {
		return userPermissionMapper.insert(record);
	}

	@Override
	public int insertSelective(UserPermission record) {
		return userPermissionMapper.insertSelective(record);
	}

	@Override
	public List<UserPermission> selectByGroupOrUserId(Integer id) {
		return userPermissionMapper.selectByGroupOrUserId(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserPermission record) {
		return userPermissionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserPermission record) {
		return userPermissionMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<String> selectPrivilegeUrlByGroupOrUserId(Integer userid) {
		return userPermissionMapper.selectPrivilegeUrlByGroupOrUserId(userid);
	}

}
