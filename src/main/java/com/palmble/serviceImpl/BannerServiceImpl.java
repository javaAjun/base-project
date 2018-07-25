package com.palmble.serviceImpl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageInfo;
import com.palmble.dal.BannerMapper;
import com.palmble.entity.Banner;
import com.palmble.service.BannerService;
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
		List<Banner> list = bannerMapper.getBannerList(map);
		PageInfo<Banner> pageSource=new PageInfo<>(list);
		return pageSource;
	}
	
}
