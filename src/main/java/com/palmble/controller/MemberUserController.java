package com.palmble.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
//		 Map<String,Object> m=new HashMap<String,Object>();
//		 m.put("records", ""+s.size());
//		 m.put("root", s.subList(0, 10));
//		 m.put("page", map.get("page"));	
//		 m.put("total", ""+5);
		return m;
	}
	
}
