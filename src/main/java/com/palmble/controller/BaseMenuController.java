package com.palmble.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
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
	 * @return 
	 * @date 2018年6月15日
	 */
	@RequestMapping("/list")
	public PageInfo<BaseMenu> getMenuList(@RequestParam Map<String, String> map) {
		//PageHelper.offsetPage(0, 10);
	  	PageInfo<BaseMenu> pageSource = permissionMenuService.getMenuList(map);
	  	return pageSource;
	}
	
	/**
	 * <p>Title: 菜单添加</p>   
	 * @author WangYanke  
	 * @return 
	 * @date 2018年6月15日
	 */
	@RequestMapping("/addMenu")
	public ResultInfo addMenu(BaseMenu baseMenu) {
		return permissionMenuService.addMenu(baseMenu);
	}
	
	/**
	 * <p>Title: 菜单删除</p>   
	 * @author WangYanke  
	 * @return 
	 * @date 2018年6月15日
	 */
	@RequestMapping("delMenu")
	public ResultInfo delMenu(@RequestParam Integer id) {
		return permissionMenuService.deleteMenu(id);
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
	public ResultInfo forBiddenMenu(@RequestParam Integer menuId,Integer isDisplay) {
		return permissionMenuService.forBiddenMenu(menuId,isDisplay);
	}
	
	/**
	 * <p>Title: 将菜单设置为无效状态</p>   
	 * @author WangYanke  
	 * @date 2018年6月19日
	 */
	@RequestMapping("/noAvailMenu")
	public ResultInfo noAvailMenu(@RequestParam Integer menuId,Integer idEffective) {
		return permissionMenuService.noAvailMenu(menuId,idEffective);
	}
	
	/**
	 * <p>Title: 获取所有菜单</p>   
	 * @author WangYanke  
	 * @return 
	 * @date 2018年6月22日
	 */
	@RequestMapping("/getAllMenu")
	public String getAllMenu() {
		Map<String,String> contMap=new HashMap<String,String>();
		contMap.put("parentId", "0");
		PageInfo<BaseMenu> menuList = permissionMenuService.getMenuList(contMap);//获取一级菜单
		List<List<Object>> list = new ArrayList<>();
		for (int i = 0; i < menuList.getList().size(); i++) {
			List<Object> list1 = new ArrayList<>();
			contMap.clear();
			contMap.put("parentId", menuList.getList().get(i).getParentId()+"");
			PageInfo<BaseMenu> childMenuList = permissionMenuService.getMenuList(contMap);//获取二级菜单
			for (int j = 0; j <childMenuList.getList().size(); j++) {
				contMap.clear();
				contMap.put("parentId", childMenuList.getList().get(j).getParentId()+"");
				PageInfo<BaseMenu> authorityList = permissionMenuService.getMenuList(contMap);
				list1.add(authorityList.getList());
			}
			list.add(list1);
		}
		String listJson = JSON.toJSONString(list);
		return listJson;
	}
}
