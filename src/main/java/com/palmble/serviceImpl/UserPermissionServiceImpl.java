package com.palmble.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<Integer> selectPrivilegeUrlByGroupOrUserId(Integer userid) {
		return userPermissionMapper.selectPrivilegeUrlByGroupOrUserId(userid);
	}
/**
 * 检查该分组是否有访问权限
 */
	@Override
	public Boolean privilegeStatus(Integer groupId, String path) {
		if(path.endsWith(".html")&&path.startsWith("/")) {
			path=path.substring(1);
		}
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("groupId", groupId);
		param.put("path", path);
		Map<String,Object> map=userPermissionMapper.getByUserIdAndUrl(param);
		if(map==null) {
			return false;
		}
		return !map.isEmpty();
	}

}
