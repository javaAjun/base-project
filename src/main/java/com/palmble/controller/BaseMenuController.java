package com.palmble.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
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
	@ResponseBody
	public String getAllMenu() {
		Map<String,String> contMap=new HashMap<String,String>();
		contMap.put("parentId", "0");
		PageInfo<BaseMenu> menuList = permissionMenuService.getMenuList(contMap);//获取一级菜单
		List<Map<String,Object>> baseList = new ArrayList<>();
		for (int i = 0; i < menuList.getList().size(); i++) {
			Map<String,Object> parentMap= new HashMap<String,Object>();
			contMap.clear();
			contMap.put("parentId", menuList.getList().get(i).getId()+"");
			PageInfo<BaseMenu> childMenuList = permissionMenuService.getMenuList(contMap);//获取二级菜单
			parentMap.put("baseId", menuList.getList().get(i).getId());
			parentMap.put("baseMenu", menuList.getList().get(i).getMenuName());
			String childJson = JSON.toJSONString(childMenuList.getList());//序列化json
			parentMap.put("baseInfo",childJson);
			baseList.add(parentMap);
		}
		String baseJson = JSON.toJSONString(baseList);//序列化json
		
		return baseJson;
	}
}
