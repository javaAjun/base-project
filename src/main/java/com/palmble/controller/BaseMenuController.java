package com.palmble.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.palmble.base.PalmbleBaseController;
import com.palmble.entity.BaseMenu;
import com.palmble.service.BaseMenuService;
import com.palmble.service.UserPermissionService;
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
public class BaseMenuController extends PalmbleBaseController{
	/* 本地异常日志记录对象 */
	private static final Logger logger = LoggerFactory
			.getLogger(BaseMenuController.class);
	@Autowired 
	BaseMenuService permissionMenuService;
	
	@Autowired
	private UserPermissionService userPermissionService;
	/**
	 * <p>Title: 获取菜单信息列表</p>   
	 * @author WangYanke  
	 * @return 
	 * @date 2018年6月15日
	 */
	@RequestMapping("/list")
	public PageInfo<BaseMenu> getMenuList(@RequestParam Map<String, String> map) {
		PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("rows").toString()),map.get("sidx").toString());
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
		permissionMenuService.getMenuInfoByselect(baseMenu);
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
	public String getAllMenu(@RequestParam("userid")Integer userid) {
		if(userid==-1) {
			userid=(Integer)getSession().getAttribute("groupId");
		}
		logger.debug("*************************************"+userid+"***************************");
		List<Integer> qxlist = userPermissionService.selectPrivilegeUrlByGroupOrUserId(userid);
		Map<String,String> contMap=new HashMap<String,String>();
		contMap.put("parentId", "0");
		contMap.put("remake", "0");
		contMap.put("sidx", "sequence_number");
		PageHelper.startPage(0,100,"sequence_number");
		PageInfo<BaseMenu> menuList = permissionMenuService.getMenuList(contMap);//获取一级菜单
		List<Map<String,Object>> baseList = new ArrayList<>();
		for (int i = 0; i < menuList.getList().size(); i++) {
			Map<String,Object> parentMap= new HashMap<String,Object>();
			contMap.clear();
			contMap.put("parentId", menuList.getList().get(i).getId()+"");
			contMap.put("remake", "0");
			PageHelper.startPage(0,100,"sequence_number");
			PageInfo<BaseMenu> childMenuList = permissionMenuService.getMenuList(contMap);//获取二级菜单
			parentMap.put("menuId", menuList.getList().get(i).getId());
			parentMap.put("menuName", menuList.getList().get(i).getMenuName());
			if(qxlist.contains(menuList.getList().get(i).getId())) {
				parentMap.put("flag", "true");
			}else {
				parentMap.put("flag", "false");
			}
			List<Map<String,Object>> parentList = new ArrayList<>();
			for (int j = 0; j < childMenuList.getList().size(); j++) {
				contMap.clear();
				contMap.put("parentId", childMenuList.getList().get(j).getId()+"");
				contMap.put("remake", "0");
				PageHelper.startPage(0,100,"sequence_number");
				PageInfo<BaseMenu> list = permissionMenuService.getMenuList(contMap);//获取二级菜单
				List<Map<String,Object>> childList = new ArrayList<>();
				for(int k=0;k<list.getList().size();k++) {
					Map<String,Object> childMap= new HashMap<String,Object>();
					childMap.put("menuId", list.getList().get(k).getId());
					childMap.put("menuName", list.getList().get(k).getMenuName());
					childMap.put("menuUrl", list.getList().get(k).getUrl());
					if(qxlist.contains(list.getList().get(k).getId())) {
						childMap.put("flag", "true");
					}else {
						childMap.put("flag", "false");
					}
					childList.add(childMap);
				}
				String listJson = JSON.toJSONString(childList);//序列化json
				Map<String,Object> menuMap= new HashMap<String,Object>();
				menuMap.put("menuId",childMenuList.getList().get(j).getId());
				menuMap.put("menuName",childMenuList.getList().get(j).getMenuName());
				menuMap.put("menuUrl",childMenuList.getList().get(j).getUrl());
				menuMap.put("menuInfo",listJson);
				if(qxlist.contains(childMenuList.getList().get(j).getId())) {
					menuMap.put("flag", "true");
				}else {
					menuMap.put("flag", "false");
				}
				parentList.add(menuMap);
			}
			String parentJson = JSON.toJSONString(parentList);//序列化json
			//String childJson = JSON.toJSONString(childMenuList.getList());//序列化json
			parentMap.put("menuInfo",parentJson);
			baseList.add(parentMap);
		}
		String baseJson = JSON.toJSONString(baseList);//序列化json
		System.out.println(baseJson);
		return baseJson;
	}
	
	@RequestMapping("/changeMenuSort")
	public ResultInfo changeMenuSort(@RequestParam Integer id,Integer sort) {
		return permissionMenuService.changeMenuSort(id,sort);
	}
}
