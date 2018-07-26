package com.palmble.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.palmble.entity.Banner;
import com.palmble.utils.ResultInfo;

public interface BannerService {

	PageInfo<Banner> getBannerList(Map<String, Object> map);

	ResultInfo addOrEditBanner(Banner banner);

	Banner getBannerInfo(Integer id);

	ResultInfo delBanner(Integer id);

	ResultInfo uodateBannerState(Integer id, Integer bannerState);

	ResultInfo bannerChangeSort(Integer id, Integer sort); 
}
