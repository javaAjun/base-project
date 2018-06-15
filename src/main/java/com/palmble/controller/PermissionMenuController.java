package com.palmble.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.palmble.service.PermissionMenuService;

/**
* <p>Title: 权限菜单管理</p>  
* <p>Description:pamable </p>  
* @author WangYanke  
* @date 2018年6月15日 
* @version 1.0
 */
@RestController
public class PermissionMenuController {
	
	@Autowired PermissionMenuService permissionMenuService;
	/**
	 * <p>Title: 获取菜单信息列表</p>   
	 * @author WangYanke  
	 * @date 2018年6月15日
	 */
	@RequestMapping("/list")
	public void getMenuList(@RequestParam Map<String, String> map) {
	  	permissionMenuService.getMenuList(map);
	}
	
	/**
	 * <p>Title: 菜单添加</p>   
	 * @author WangYanke  
	 * @date 2018年6月15日
	 */
	@RequestMapping("/addMenu")
	public void addMenu() {
		
	}
	
	/**
	 * <p>Title: 菜单删除</p>   
	 * @author WangYanke  
	 * @date 2018年6月15日
	 */
	@RequestMapping("delMenu")
	public void delMenu() {
		
	}
}
