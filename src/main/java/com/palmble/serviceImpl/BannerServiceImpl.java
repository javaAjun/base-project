package com.palmble.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.palmble.dal.BannerMapper;
import com.palmble.entity.Banner;
import com.palmble.entity.Result;
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
	public Result addOrEditBanner(Banner banner) {
		Result result=new Result();
		int operateCount=0;//初始化添加成功/修改成功数据条数
		String url="";
		if(banner.getBannerTitle()==null||banner.getBannerTitle().equals("")) {
			result.setCode(0);
			result.setMsg("banner标题不能为空");
			return result;
		}
		if(banner.getImageUrl()==null||banner.getImageUrl().equals("")) {
			result.setCode(0);
			result.setMsg("图片不能为空");
			return result;
		}
		if(banner.getType()==0) {//跳转链接
			if(banner.getBannerUrl()==null||banner.getBannerUrl().equals("")){
				result.setCode(0);
				result.setMsg("跳转链接不能为空");
				return result;
			}else {
				String check="^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
				Pattern regex = Pattern.compile(check);
	            Matcher matcher = regex.matcher(banner.getBannerUrl());
	            if(!matcher.matches()) {
	            	result.setCode(0);
					result.setMsg("跳转链接不合法");
					return result;
	            }
			}
		}
		if(banner.getType()==1) {//关联商品
			if(banner.getGoodsId()==null){
				result.setCode(0);
				result.setMsg("请选择关联商品");
				return result;
			}
		}
		if(banner.getId()!=null) {
			operateCount = bannerMapper.updateByPrimaryKeySelective(banner);
			url="banner_add.html?id="+banner.getId();
		}else {
			Banner banne = bannerMapper.selectByName(banner);
			if(banne!=null) {
				result.setCode(0);
				result.setMsg("banner名称已存在");
				return result;
			}
			banner.setCreateTime(new Date());
			banner.setIsDelete(0);
			operateCount=bannerMapper.insert(banner);
			url="banner_add.html";
		}
		if(operateCount>0) {
			result.setCode(1);
			result.setMsg("操作成功");
			result.setUrl(url);
		}else {
			result.setCode(0);
			result.setMsg("操作失败");
		}
		return result;
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
