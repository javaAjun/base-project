package com.palmble.service;

import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.palmble.entity.BaseMenu;
import com.palmble.utils.ResultInfo;


public interface BaseMenuService {
	PageInfo<BaseMenu> getMenuList(Map<String, String> map);

	BaseMenu getMenuInfo(Integer menuId);

	ResultInfo deleteMenu(Integer menuId);

	ResultInfo forBiddenMenu(Integer menuId);

	ResultInfo noAvailMenu(Integer menuId);

	ResultInfo addMenu(BaseMenu baseMenu);
}
