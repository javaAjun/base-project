package com.palmble.serviceImpl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.palmble.dal.BaseMenuMapper;
import com.palmble.dal.UserPermissionMapper;
import com.palmble.entity.BaseMenu;
import com.palmble.service.BaseMenuService;

@Service
public class BaseMenuServiceImpl implements BaseMenuService{
	@Resource
	private BaseMenuMapper baseMenuMapper;
	/**
	 * 获取菜单列表实现方法
	 * @return 
	 * @return 
	 */
	@Override
	public Page<Object> getMenuList(Map<String, String> map) {
		return baseMenuMapper.byAllMenuList(map);
	}
	@Override
	public BaseMenu getMenuInfo(Integer menuId) {
		return baseMenuMapper.selectByPrimaryKey(menuId);
	}

}
