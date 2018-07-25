package com.palmble.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.palmble.entity.AdminUser;
import com.palmble.entity.MemberUser;
import com.palmble.service.MemberUserService;

@RestController
@RequestMapping("/member")
public class MemberUserController {
	
	@Autowired
	private MemberUserService memberUserService;
	
	@RequestMapping("/getMenberList")
	public Map<String,Object> getMenberList(@RequestParam Map<String,Object> map) {
		Map<String,Object> m=memberUserService.fuzzyQuery(map);
		return m;
	}
	@RequestMapping("/getMenberListById")
	public MemberUser  getMenberListById(String id) {
		MemberUser m=memberUserService.getById(Integer.parseInt(id));
		return m;
	}
	@RequestMapping("/updateMemberStatus")
	public Integer updateMemberStatus(Integer id, Integer status) {
		if (id == null || status == null) {
			return null;
		}
		MemberUser memberUser = new MemberUser();
		memberUser.setId(id);
		if (status == 0) {
			memberUser.setState(1);
		}
		if (status == 1) {
			memberUser.setState(0);
		}
		int resultNum = memberUserService.updateById(memberUser);
		return resultNum;
	}
	@RequestMapping("/delMember")
	public int delMember(Integer id) {
		int resultNum = memberUserService.deleteById(id);
		return resultNum;
	}
}
