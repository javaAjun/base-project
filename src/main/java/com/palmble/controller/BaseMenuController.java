package com.palmble.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.palmble.base.PalmbleBaseController;
import com.palmble.entity.BaseMenu;
import com.palmble.service.BaseMenuService;
import com.palmble.utils.ResultInfo;

/**
* <p>Title: 权限菜单管理</p>  
* <p>Description:pamable </p>  
* @author WangYanke  
* @date 2018年6月15日 
* @version 1.0
 */
@RestController
@RequestMapping("/baseMenu")
public class BaseMenuController{
	
	@Autowired BaseMenuService permissionMenuService;
	/**
	 * <p>Title: 获取菜单信息列表</p>   
	 * @author WangYanke  
	 * @date 2018年6月15日
	 */
	@RequestMapping("/list")
	public void getMenuList(@RequestParam Map<String, String> map) {
		//PageHelper.offsetPage(0, 10);
	  	PageInfo<BaseMenu> pageSource = permissionMenuService.getMenuList(map);
	  	System.out.println(pageSource.toString());
	}
	
	/**
	 * <p>Title: 菜单添加</p>   
	 * @author WangYanke  
	 * @return 
	 * @date 2018年6月15日
	 */
	@RequestMapping("/addMenu")
	public ResultInfo addMenu(@RequestParam BaseMenu baseMenu) {
		return permissionMenuService.addMenu(baseMenu);
	}
	
	/**
	 * <p>Title: 菜单删除</p>   
	 * @author WangYanke  
	 * @return 
	 * @date 2018年6月15日
	 */
	@RequestMapping("delMenu")
	public ResultInfo delMenu(@RequestParam Integer menuId) {
		return permissionMenuService.deleteMenu(menuId);
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
	
	/**
	 * <p>Title:隐藏菜单 </p>   
	 * @author WangYanke  
	 * @date 2018年6月19日
	 */
	@RequestMapping("/dispalyMenu")
	public ResultInfo forBiddenMenu(@RequestParam Integer menuId) {
		return permissionMenuService.forBiddenMenu(menuId);
	}
	
	/**
	 * <p>Title: 将菜单设置为无效状态</p>   
	 * @author WangYanke  
	 * @date 2018年6月19日
	 */
	@RequestMapping("/noAvailMenu")
	public ResultInfo noAvailMenu(@RequestParam Integer menuId) {
		return permissionMenuService.noAvailMenu(menuId);
	}
}
