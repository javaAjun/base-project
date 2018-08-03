package com.palmble.serviceImpl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageInfo;
import com.palmble.dal.BaseMenuMapper;
import com.palmble.entity.BaseMenu;
import com.palmble.entity.Result;
import com.palmble.service.BaseMenuService;
import com.palmble.utils.ResultInfo;

@Service
public class BaseMenuServiceImpl implements BaseMenuService {
	@Resource
	private BaseMenuMapper baseMenuMapper;
	/**
	 * 获取菜单列表实现方法
	 * @return 
	 * @return 
	 */
	public PageInfo<BaseMenu> getMenuList(Map<String, String> map) {
		List<BaseMenu> list = baseMenuMapper.byAllMenuList(map);
		PageInfo<BaseMenu> pageSource=new PageInfo<>(list);
		return pageSource;
	}
	public BaseMenu getMenuInfo(Integer menuId) {
		return baseMenuMapper.selectByPrimaryKey(menuId);
	}
	@Override
	public ResultInfo deleteMenu(Integer menuId) {
		 int delete = baseMenuMapper.deleteByPrimaryKey(menuId);
		 if(delete>0) {//删除成功
			 return new ResultInfo(1, "删除成功");
		 }else {
			 return new ResultInfo(-1, "删除失败");
		 }
	}
	@Override
	public ResultInfo forBiddenMenu(Integer menuId,Integer isDisplay) {
		BaseMenu menu = baseMenuMapper.selectByPrimaryKey(menuId);
		if(menu==null) {
			return new ResultInfo(-1, "获取菜单信息失败");
		}
		if(isDisplay!=null) {
			menu.setIsDisplay(isDisplay);
		}else {
			return new ResultInfo(-1, "操作失败");
		}
		int menuFlag = baseMenuMapper.updateByPrimaryKey(menu);
		if(menuFlag>0) {
			return new ResultInfo(1, "操作成功");
		 }else {
			return new ResultInfo(-1, "操作失败");
		}
	}
	@Override
	public ResultInfo noAvailMenu(Integer menuId,Integer idEffective) {
		
		BaseMenu menu = this.getMenuInfo(menuId);
		if(menu==null) {
			return new ResultInfo(-1, "获取菜单信息失败");
		}
		if(idEffective!=null) {
			menu.setIdEffective(idEffective);
		}else {
			return new ResultInfo(-1, "操作失败");
		}
		int menuFlag = baseMenuMapper.updateByPrimaryKey(menu);
		if(menuFlag>0) {
			return new ResultInfo(1, "操作成功");
		}else {
			return new ResultInfo(-1, "操作失败");
		}
	}
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public ResultInfo addMenu(BaseMenu baseMenu) {
		int operateCount=0;//初始化添加成功/修改成功数据条数
		if(baseMenu.getId()!=null&&!baseMenu.getId().equals("")) {
			operateCount = baseMenuMapper.updateByPrimaryKey(baseMenu);
		}else {
			operateCount = baseMenuMapper.insert(baseMenu);
		}
		if(operateCount>0) {
			return new ResultInfo(1, "操作成功");
		}else {
			return new ResultInfo(-1, "操作失败");
		}
	}
	@Override
	public List<BaseMenu> getAll() {
		return baseMenuMapper.getAll();
	}
	@Override
	public BaseMenu getMenuInfoByselect(BaseMenu baseMenu) {
		return baseMenuMapper.selectBySelective(baseMenu);
		
	}
}
