package com.palmble.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.palmble.entity.AdminGroups;
import com.palmble.entity.Result;
import com.palmble.service.AdminGroupsService;
import com.palmble.utils.DateUtil;

@RestController
@RequestMapping("/groups")
public class AdminGroupsController {
	@Autowired
	private AdminGroupsService groupService;
	@RequestMapping("/getGroupList")
	public List<AdminGroups> getGroupList(@RequestParam Map<String,Object> map){
		return groupService.find(map);
	}
	@RequestMapping("/edit")
	public Result edit(AdminGroups group,String url) {
		int state=groupService.updateById(group);
		Result result=new Result();
		if(state!=1) {
			result.setCode(0);
			result.setMsg("操作失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return result;
		}
		result.setCode(1);
		result.setUrl(url);
		result.setMsg("操作成功");
		return result;
	}
	@Transactional
	@RequestMapping("/add")
	public Result add(AdminGroups group,HttpServletRequest request,String url) {
		Result result=new Result();
		if(group==null) {
			result.setCode(0);
			result.setMsg("操作失败");
			return result;
		}
		group.setCreateByUserName((String)request.getSession().getAttribute("userName"));
		group.setCreateTime(DateUtil.getCurrentDateTime());
		int state=groupService.insert(group);
		if(state!=1) {
			result.setCode(0);
			result.setMsg("操作失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return result;
		}
		result.setCode(1);
		result.setUrl(url);
		result.setMsg("操作成功");
		return result;
	}
	@Transactional
	@RequestMapping("/del")
	public Result del(Integer id) {
		Result result=new Result();
		int state=groupService.deleteById(id);
		if(state!=1) {
			result.setCode(0);
			result.setMsg("操作失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return result;
		}
		result.setCode(1);
		result.setMsg("操作成功");
		return result;
	}
	@RequestMapping("/getGroup")
	public AdminGroups getGroup(HttpServletRequest request) {
		Integer id=(Integer)request.getSession().getAttribute("groupId");
		return groupService.getById(id);
	}
}
