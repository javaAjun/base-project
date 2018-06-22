package com.palmble.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.palmble.entity.AdminUser;
import com.palmble.entity.Result;
import com.palmble.service.AdminUserService;

@RestController
public class AdminController {
	@Autowired
	private AdminUserService adminUserService;
	@RequestMapping("/getAdminList")
	public List<AdminUser> getAdminList(@RequestParam Map<String,Object> map) {
		List<AdminUser> list=adminUserService.selectJqGridByPeagSelective(map);
		return list;
	}
	@RequestMapping("/updateAdminStatus")
	public Integer updateAdminList(Integer id,Integer status) {
		if(id==null||status==null) {
			return null;
		}
		AdminUser adminUser=new AdminUser();
		adminUser.setId(id);
		if(status==0) {
			adminUser.setEnableFlag(1);
		}
		if(status==1) {
			adminUser.setEnableFlag(0);
		}
		int resultNum=adminUserService.updateByPrimaryKeySelective(adminUser);
		return resultNum;
	}
	@RequestMapping("/delAdmin")
	public int delAdmin(Integer id) {
		int resultNum=adminUserService.deleteByPrimaryKey(id);
		return resultNum;
	}
	@RequestMapping("/add_or_edit")
	public Result add_or_edit(AdminUser user,String url) {
		Result result=new Result();
		int statusNum=0;
		if(user==null) {
			result.setCode(0);
			result.setMsg("操作失败!");
			return result;
		}
		if(user.getId()==null||user.getId().equals("")) {
			statusNum=adminUserService.insertSelective(user);
			
		}else {
			statusNum=adminUserService.updateByPrimaryKeySelective(user);
		}
		result.setCode(statusNum);
		if(statusNum==1) {
			result.setMsg("操作成功");
			result.setUrl(url);
		}else {
			result.setMsg("操作失败");
		}
		return result;
	}
}
