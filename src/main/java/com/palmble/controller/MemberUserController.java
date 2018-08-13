package com.palmble.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.palmble.entity.MemberUser;
import com.palmble.entity.Result;
import com.palmble.service.MemberUserService;
import com.palmble.utils.AccountValidatorUtil;
import com.palmble.utils.DateUtil;

@RestController
@RequestMapping("/member")
public class MemberUserController {
	
	@Autowired
	private MemberUserService memberUserService;
	
	@RequestMapping("/getMenberList")
	public PageInfo<MemberUser> getMenberList(@RequestParam Map<String,Object> map) {
		PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("rows").toString()),
				map.get("sidx").toString()+" "+map.get("sord").toString());
		List<MemberUser> list=memberUserService.fuzzyQuery(map);
		PageInfo<MemberUser> page=new PageInfo<MemberUser>(list);
		return page;
	}
	@RequestMapping("/getMenberListById")
	public MemberUser  getMenberListById(String id) {
		MemberUser m=memberUserService.getById(Integer.parseInt(id));
		return m;
	}
//	@RequestMapping("/updateMemberStatus")
//	public Integer updateMemberStatus(Integer id, Integer status) {
//		if (id == null || status == null) {
//			return null;
//		}
//		MemberUser memberUser = new MemberUser();
//		memberUser.setId(id);
//		if (status == 0) {
//			memberUser.setState(1);
//		}
//		if (status == 1) {
//			memberUser.setState(0);
//		}
//		int resultNum = memberUserService.updateById(memberUser);
//		return resultNum;
//	}
	@RequestMapping("/delMember")
	public int delMember(Integer id) {
		int resultNum = memberUserService.deleteById(id);
		return resultNum;
	}
	@RequestMapping("/edit")
	public Result edit(MemberUser user) {
		Result result=new Result();
		if(user==null) {
			result.setCode(0);
			result.setMsg("操作失败");
			return result;
		}
		String phone=user.getPhone();
		if(phone!=null&&!phone.equals("")&&!AccountValidatorUtil.isMobile(phone)) {
			result.setCode(0);
			result.setMsg("请输入正确的手机号码!");
			return result;
		}
		String email=user.getEMail();
		if(email!=null&&!email.equals("")&&!AccountValidatorUtil.isEmail(email)) {
			result.setCode(0);
			result.setMsg("请输入正确的邮箱!");
			return result;
		}
		String idNumber=user.getIdNumber();
		if(idNumber!=null&&!idNumber.equals("")&&!AccountValidatorUtil.isIDCard(idNumber)) {
			result.setCode(0);
			result.setMsg("请输入正确的身份证!");
			return result;
		}
		String qq=user.getQq();
		if(qq!=null&&!qq.equals("")&&!AccountValidatorUtil.isQQ(qq)) {
			result.setCode(0);
			result.setMsg("请输入正确的QQ号码!");
			return result;
		}
		user.setUpdateTime(DateUtil.getCurrentDateTime());
		int resultNum = memberUserService.updateById(user);
		if(resultNum==1) {
			result.setCode(1);
			result.setMsg("操作成功!");
		}else {
			result.setCode(0);
			result.setMsg("操作失败");
		}
		return result;
	}
}
