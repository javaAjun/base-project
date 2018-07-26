package com.palmble.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.palmble.dal.BannerMapper;
import com.palmble.entity.Banner;
import com.palmble.service.BannerService;
import com.palmble.utils.ResultInfo;
/**
* <p>Title: BannerServiceImpl</p>  
* <p>Description:pamable </p>  
* @author WangYanke  
* @date 2018年7月24日 
* @version 1.0
 */
@Service
public  class BannerServiceImpl implements BannerService {
	@Resource
	private BannerMapper bannerMapper;
	public PageInfo<Banner> getBannerList(Map<String, Object> map) {
		PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("rows").toString()));
		List<Banner> list = bannerMapper.getBannerList(map);
		PageInfo<Banner> pageSource=new PageInfo<>(list);
		return pageSource;
	}
	public ResultInfo addOrEditBanner(Banner banner) {
		int operateCount=0;//初始化添加成功/修改成功数据条数
		if(banner.getId()!=null&&!banner.getId().equals("")) {
			operateCount = bannerMapper.updateByPrimaryKey(banner);
		}else {
			banner.setCreateTime(new Date());
			banner.setIsDelete(0);
			bannerMapper.insert(banner);
		}
		if(operateCount>0) {
			return new ResultInfo(1, "操作成功");
		}else {
			return new ResultInfo(-1, "操作失败");
		}
	}
	
	public Banner getBannerInfo(Integer id) {
		return bannerMapper.selectByPrimaryKey(id);
	}
	
	public ResultInfo delBanner(Integer id) {
		
		int delete = bannerMapper.deleteByPrimaryKey(id);
		if(delete>0) {//删除成功
			 return new ResultInfo(1, "删除成功");
		 }else {
			 return new ResultInfo(-1, "删除失败");
		 }
	}
	
	public ResultInfo uodateBannerState(Integer id, Integer bannerState) {
		Banner banner = bannerMapper.selectByPrimaryKey(id);
		if(banner==null) {
			return new ResultInfo(-1, "获取菜单信息失败");
		}
		if(bannerState!=null) {
			banner.setState(bannerState);
		}else {
			return new ResultInfo(-1, "操作失败");
		}
		int num = bannerMapper.updateByPrimaryKey(banner);
		if(num>0) {
			return new ResultInfo(1, "操作成功");
		}else {
			return new ResultInfo(-1, "操作失败");
		}
	}
	@Override
	public ResultInfo bannerChangeSort(Integer id, Integer sort) {
		Banner banner = bannerMapper.selectByPrimaryKey(id);
		if(banner==null) {
			return new ResultInfo(-1, "获取菜单信息失败");
		}
		if(sort!=null) {
			banner.setSequence(sort);
		}else {
			return new ResultInfo(-1, "操作失败");
		}
		int num = bannerMapper.updateByPrimaryKey(banner);
		if(num>0) {
			return new ResultInfo(1, "操作成功");
		}else {
			return new ResultInfo(-1, "操作失败");
		}
	}
	
}
