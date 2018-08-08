package com.palmble.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.palmble.annotation.CustomLog;
import com.palmble.base.PalmbleBaseController;
import com.palmble.entity.AdminGroups;
import com.palmble.entity.AdminUser;
import com.palmble.entity.Result;
import com.palmble.enums.LogEnum;
import com.palmble.service.AdminGroupsService;
import com.palmble.service.AdminUserService;

@RestController
public class LoginController extends PalmbleBaseController{
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminGroupsService groupService;
	@PostMapping("/toLogin")
	@CustomLog(logEnum=LogEnum.PUBLIC,module="登录模块",desc="管理员登录")
	public Result login(@RequestParam Map<String,Object> map,HttpServletRequest request,HttpServletResponse response) {
		Result result=new Result();
		String inputVerify=(String)map.get("verify");
		if(inputVerify==null||inputVerify.trim().equals("")) {
			result.setCode(3);
			result.setMsg("验证码不允许为空!");
			return result;
		}
		String vifityCode=(String)request.getSession().getAttribute("vifityCode");
		if(vifityCode==null||vifityCode.trim().equals("")) {
			result.setCode(3);
			result.setMsg("验证码失效!");
			return result;
		}
		if(!inputVerify.equals(vifityCode)) {
			result.setCode(3);
			result.setMsg("验证码错误!");
			return result;
		}
		String inuputUsername=(String)map.get("username");
		String inputPassword=(String)map.get("password");
		if(inuputUsername==null||inuputUsername.trim().equals("")) {
			result.setCode(2);
			result.setMsg("用户名不能为空!");
			return result;
		}
		if(inputPassword==null||inputPassword.trim().equals("")) {
			result.setCode(2);
			result.setMsg("密码不能为空!");
			return result;
		}
		inputPassword=DigestUtils.md5Hex("palmble"+inputPassword);
		AdminUser adminUser=adminUserService.selectOne("loginiNo", inuputUsername);
		if(adminUser==null) {
			result.setCode(2);
			result.setMsg("用户名不存在!");
			return result;
		}
		if(!inputPassword.equals(adminUser.getPwd())) {
			result.setCode(2);
			result.setMsg("密码不正确!");
			return result;
		}
		Integer enableFlag=adminUser.getEnableFlag();
		if(enableFlag!=null&&enableFlag==0) {
			result.setCode(2);
			result.setMsg("账号已禁用!");
			return result;
		}
		request.getSession().setAttribute("userId", adminUser.getId());
		request.getSession().setAttribute("userName", adminUser.getLoginiNo());
		request.getSession().setAttribute("groupId", adminUser.getGroupId());
		AdminGroups group=groupService.getById(adminUser.getGroupId());
		Integer groupState=null;
		if(group!=null) {
			groupState=group.getEnableFlag();
			response.addCookie(new Cookie("userName",adminUser.getLoginiNo()));
			response.addCookie(new Cookie("groupName",group.getGroupName()));
		}
		if(groupState!=null&&groupState==0) {
			result.setCode(2);
			result.setMsg("您所在分组已被禁用!");
			return result;
		}
		Integer loginCount=adminUser.getLoginCount()==null?0:1;
		adminUser.setLoginCount(loginCount+1);
		adminUser.setLastLoginIp(getUserIP(request));
		adminUser.setLastLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		adminUserService.updateByPrimaryKey(adminUser);
		result.setCode(1);
		result.setMsg("success");
		result.setUrl("html/index.html");
		return result;
	}
}
