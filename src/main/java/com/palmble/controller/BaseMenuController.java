package com.palmble.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.palmble.entity.BaseMenu;
import com.palmble.service.BaseMenuService;

/**
* <p>Title: 权限菜单管理</p>  
* <p>Description:pamable </p>  
* @author WangYanke  
* @date 2018年6月15日 
* @version 1.0
 */
@RestController
@RequestMapping("/baseMenu")
public class BaseMenuController {
	
	@Autowired BaseMenuService permissionMenuService;
	/**
	 * <p>Title: 获取菜单信息列表</p>   
	 * @author WangYanke  
	 * @date 2018年6月15日
	 */
	@RequestMapping("/list")
	public void getMenuList(@RequestParam Map<String, String> map) {
	  	Page<Object> page = permissionMenuService.getMenuList(map);
	  	System.out.println(page.toString());
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
	
	/**
	 * <p>Title: 获取菜单信息</p>   
	 * @author WangYanke  
	 * @return 
	 * @date 2018年6月15日
	 */
	@RequestMapping("/menuInfo")
	public BaseMenu getMenu(@RequestParam Integer menuId) {
		return permissionMenuService.getMenuInfo(menuId);
	}
}
