package com.palmble.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.palmble.entity.BaseMenu;
import com.palmble.utils.ResultInfo;


public interface BaseMenuService {
	Page<Object> getMenuList(Map<String, String> map);

	BaseMenu getMenuInfo(Integer menuId);

	ResultInfo deleteMenu(Integer menuId);

	ResultInfo forBiddenMenu(Integer menuId);

	ResultInfo noAvailMenu(Integer menuId);
}
