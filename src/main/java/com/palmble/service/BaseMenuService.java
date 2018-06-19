package com.palmble.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.palmble.entity.BaseMenu;


public interface BaseMenuService {
	Page<Object> getMenuList(Map<String, String> map);

	BaseMenu getMenuInfo(Integer menuId);
}
