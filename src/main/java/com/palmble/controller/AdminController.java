package com.palmble.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.palmble.entity.AdminUser;
import com.palmble.entity.Result;
import com.palmble.entity.UserPermission;
import com.palmble.service.AdminUserService;
import com.palmble.service.UserPermissionService;

@RestController
public class AdminController {
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private UserPermissionService userPermissionService;

	@RequestMapping("/getAdminList")
	public List<AdminUser> getAdminList(@RequestParam Map<String, Object> map) {
		List<AdminUser> list = adminUserService.selectJqGridByPeagSelective(map);
		return list;
	}

	@RequestMapping("/updateAdminStatus")
	public Integer updateAdminList(Integer id, Integer status) {
		if (id == null || status == null) {
			return null;
		}
		AdminUser adminUser = new AdminUser();
		adminUser.setId(id);
		if (status == 0) {
			adminUser.setEnableFlag(1);
		}
		if (status == 1) {
			adminUser.setEnableFlag(0);
		}
		int resultNum = adminUserService.updateByPrimaryKeySelective(adminUser);
		return resultNum;
	}

	@RequestMapping("/delAdmin")
	public int delAdmin(Integer id) {
		int resultNum = adminUserService.deleteByPrimaryKey(id);
		return resultNum;
	}

	@RequestMapping("/add_or_edit")
	public Result add_or_edit(AdminUser user, String url) {
		Result result = new Result();
		int statusNum = 0;
		if (user == null) {
			result.setCode(0);
			result.setMsg("操作失败!");
			return result;
		}
		if (user.getId() == null || user.getId().equals("")) {
			AdminUser adminTemplate=adminUserService.selectOne("loginiNo", user.getLoginiNo());
			if(adminTemplate==null) {
				statusNum = adminUserService.insertSelective(user);
			}else {
				result.setCode(0);
				result.setMsg("账号已存在!");
				return result;
			}
		} else {
			statusNum = adminUserService.updateByPrimaryKeySelective(user);
		}
		result.setCode(statusNum);
		if (statusNum == 1) {
			result.setMsg("操作成功");
			result.setUrl(url);
		} else {
			result.setMsg("操作失败");
		}
		return result;
	}

	@RequestMapping("/updatePassword")
	public Result updatePassword(HttpServletRequest request,String old_pass,String password,String password_confirm) {
		Result result=new Result();
		int statusNum=0;
		if(old_pass==null||old_pass.trim().equals("")
		||password_confirm==null||password_confirm.trim().equals("")) {
			result.setCode(0);
			result.setMsg("请将页面信息补充完整");
			return result;
		}
		if(!password.equals(password_confirm)) {
			result.setCode(0);
			result.setMsg("确认密码不一致,请重新输入!");
			return result;
		}
		String username=(String)request.getSession().getAttribute("loginNo");
		AdminUser admin=adminUserService.selectOne("loginiNo", username);
		if(!admin.getPwd().equals(old_pass)) {
			result.setCode(0);
			result.setMsg("密码有误,应重新输入!");
			return result;
		}
		admin.setPwd(password_confirm);
		statusNum=adminUserService.updateByPrimaryKeySelective(admin);
		if(statusNum!=1) {
			result.setCode(0);
			result.setMsg("请将刷新页面重试");
			return result;
		}
		result.setCode(statusNum);
		result.setMsg("操作成功");
		result.setUrl("../login.html");
		return result;
	}
	@RequestMapping("/getPrivilegeList")
	public List<String> getPrivilegeList(String userId) {
		 List<String> list= userPermissionService.selectPrivilegeUrlByGroupOrUserId(Integer.parseInt(userId));
		return list;
	}
	@RequestMapping("/addOreditRule")
	public Result addOreditRule(Integer userId,@RequestParam List<String> urls) {
		Result result=new Result();
		if(userId==null) {
			result.setCode(0);
			result.setMsg("操作失败");
			return result;
		}
		List<UserPermission> list=userPermissionService.selectByGroupOrUserId(userId);
		List<String> urlList=new ArrayList<String>();
		for(UserPermission userPermission:list) {
			if(!urls.contains(userPermission.getPrivilegeUrl())) {
				userPermissionService.deleteByPrimaryKey(userPermission.getId());
			}else {
				urlList.add(userPermission.getPrivilegeUrl());
			}
		}
		urls.removeAll(urlList);
		for(String url:urls) {
				UserPermission userPermission=new UserPermission();
				userPermission.setGroupOrUserId(userId);
				userPermission.setPrivilegeUrl(url);
				userPermissionService.insertSelective(userPermission);
		}
		result.setCode(1);
		result.setMsg("操作成功");
		result.setUrl("admin_auth_manager.html");
		return result;
	}
}
