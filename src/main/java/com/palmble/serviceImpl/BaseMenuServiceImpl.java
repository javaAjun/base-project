package com.palmble.serviceImpl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.palmble.dal.BaseMenuMapper;
import com.palmble.entity.BaseMenu;
import com.palmble.service.BaseMenuService;
import com.palmble.utils.ResultInfo;

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
	public PageInfo<BaseMenu> getMenuList(Map<String, String> map) {
		List<BaseMenu> list = baseMenuMapper.byAllMenuList(map);
		for (int i = 0; i < list.size(); i++) {
			BaseMenu baseMenu = list.get(0);
			System.out.println(baseMenu);
			
		}
		PageInfo<BaseMenu> pageSource=new PageInfo<>(list);
		return pageSource;
	}
	@Override
	public BaseMenu getMenuInfo(Integer menuId) {
		return baseMenuMapper.selectByPrimaryKey(menuId);
	}
	@Override
	public ResultInfo deleteMenu(Integer menuId) {
		 int delete = baseMenuMapper.deleteByPrimaryKey(menuId);
		 if(delete>0) {//删除成功
			 return new ResultInfo(0, "删除成功");
		 }else {
			 return new ResultInfo(-1, "删除失败");
		 }
	}
	@Override
	public ResultInfo forBiddenMenu(Integer menuId) {
		BaseMenu menu = baseMenuMapper.selectByPrimaryKey(menuId);
		if(menu==null) {
			return new ResultInfo(-1, "获取菜单信息失败");
		}
		menu.setIsDisplay(1);
		int menuFlag = baseMenuMapper.updateByPrimaryKey(menu);
		if(menuFlag>0) {
			return new ResultInfo(0, "操作成功");
		 }else {
			return new ResultInfo(-1, "操作失败");
		}
	}
	@Override
	public ResultInfo noAvailMenu(Integer menuId) {
		
		BaseMenu menu = this.getMenuInfo(menuId);
		if(menu==null) {
			return new ResultInfo(-1, "获取菜单信息失败");
		}
		menu.setIdEffective(1);
		int menuFlag = baseMenuMapper.updateByPrimaryKey(menu);
		if(menuFlag>0) {
			return new ResultInfo(0, "操作成功");
		}else {
			return new ResultInfo(-1, "操作失败");
		}
	}
	@Override
	public ResultInfo addMenu(BaseMenu baseMenu) {
		int menuNumber = baseMenuMapper.insert(baseMenu);
		if(menuNumber>0) {
			return new ResultInfo(0, "添加菜单成功");
		}else {
			return new ResultInfo(-1, "添加菜单失败");
		}
	}
}
