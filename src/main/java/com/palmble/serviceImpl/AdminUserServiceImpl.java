package com.palmble.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmble.dal.AdminUserMapper;
import com.palmble.entity.AdminUser;
import com.palmble.service.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService {
	@Autowired
	private AdminUserMapper adminUserMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return adminUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AdminUser record) {
		return adminUserMapper.insert(record);
	}

	@Override
	public int insertSelective(AdminUser record) {
		return adminUserMapper.insertSelective(record);
	}

	@Override
	public AdminUser selectByPrimaryKey(Integer id) {
		return adminUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AdminUser record) {
		return adminUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AdminUser record) {
		return adminUserMapper.updateByPrimaryKey(record);
	}

}
