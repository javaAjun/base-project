package com.palmble.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmble.dal.MemberUserDao;
import com.palmble.entity.MemberUser;
import com.palmble.service.MemberUserService;
@Service
public class MemberUserServiceImpl implements MemberUserService{
	@Autowired
	private MemberUserDao MemberUserDao;
	@Override
	public List<MemberUser> getAllMemberList() {
		return MemberUserDao.find(null);
	}
	@Override
	public List<MemberUser> fuzzyQuery(Map<String, Object> params) {
		List<MemberUser> list=MemberUserDao.fuzzyQuery(params);
		return list;
	}
	@Override
	public int updateById(MemberUser memberUser) {
		return MemberUserDao.updateById(memberUser);
	}
	@Override
	public int insert(MemberUser memberUser) {
		return MemberUserDao.insert(memberUser);
	}
	@Override
	public int insertFully(MemberUser memberUser) {
		return MemberUserDao.insertFully(memberUser);
	}
	@Override
	public int deleteById(Integer id) {
		return MemberUserDao.deleteById(id);
	}
	@Override
	public int updateFullyById(MemberUser memberUser) {
		return MemberUserDao.updateFullyById(memberUser);
	}
	@Override
	public MemberUser getById(Integer id) {
		return MemberUserDao.getById(id);
	}
	@Override
	public List<MemberUser> find(Map<String, Object> params) {
		return MemberUserDao.find(params);
	}
	@Override
	public int count(Map<String, Object> params) {
		return MemberUserDao.count(params);
	}

}
