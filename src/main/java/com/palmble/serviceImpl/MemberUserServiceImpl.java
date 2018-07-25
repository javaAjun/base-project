package com.palmble.serviceImpl;

import java.util.HashMap;
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
	public Map<String,Object> fuzzyQuery(Map<String, Object> params) {
		String rows=(String)params.get("rows");
		String page=(String)params.get("page");
		int startRow=0;
		int pageSize=0;
		int endRow=0;
		if(rows!=null&&page!=null) {
			pageSize=Integer.parseInt(rows);
			startRow=pageSize*Integer.parseInt(page)-pageSize;
			endRow=startRow+pageSize;
		}
		List<MemberUser> list=MemberUserDao.fuzzyQuery(params);
		 Map<String,Object> m=new HashMap<String,Object>();
		 if(list!=null) {
			 try {
			 int records=list.size();
			 m.put("records", records);
			 if(endRow>records) {
				 endRow=records;
			 }
			 m.put("rows", list.subList(startRow, endRow));
			 int total=list.size()/pageSize;
			 total=list.size()%pageSize==0?total:total+1;
			 m.put("total",total);
			 }catch(Exception e) {
				 e.printStackTrace();
			 }
		 }
		return m;
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
