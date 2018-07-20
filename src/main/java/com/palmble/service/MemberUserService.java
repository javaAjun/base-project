package com.palmble.service;

import java.util.List;
import java.util.Map;

import com.palmble.entity.MemberUser;

public interface MemberUserService {
	List<MemberUser> getAllMemberList();
	Map<String,Object> fuzzyQuery(Map<String, Object> params);
}
