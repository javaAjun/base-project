package com.palmble.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.palmble.entity.BaseMenu;
import com.palmble.utils.ResultInfo;


public interface BaseMenuService {
	PageInfo<BaseMenu> getMenuList(Map<String, String> map);

	BaseMenu getMenuInfo(Integer menuId);

	ResultInfo deleteMenu(Integer menuId);

	ResultInfo forBiddenMenu(Integer menuId,Integer isDisplay);

	ResultInfo noAvailMenu(Integer menuId,Integer idEffective);

	ResultInfo addMenu(BaseMenu baseMenu);
	
	List<BaseMenu> getAll();

	BaseMenu getMenuInfoByselect(BaseMenu baseMenu);
}
