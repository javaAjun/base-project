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
		int startRows=0;
		int pageSize=0;
		if(rows!=null&&page!=null) {
			pageSize=Integer.parseInt(rows);
			startRows=pageSize*Integer.parseInt(page)-pageSize;
		}
		List<MemberUser> list=MemberUserDao.fuzzyQuery(params);
		 Map<String,Object> m=new HashMap<String,Object>();
		 if(list!=null) {
			 m.put("records", list.size());
			 m.put("rows", list.subList(pageSize, startRows));
			 m.put("total", list.size()/pageSize);
		 }
		return m;
	}

}
