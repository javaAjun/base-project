package com.palmble.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.palmble.entity.Banner;

public interface BannerService {

	PageInfo<Banner> getBannerList(Map<String, Object> map); 
}
