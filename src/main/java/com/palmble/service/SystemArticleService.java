package com.palmble.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.palmble.entity.Result;
import com.palmble.entity.SystemArticle;
import com.palmble.utils.ResultInfo;

public interface SystemArticleService {

	/**
	 * <p>Title: 获取系统文章列表</p>   
	 * @author WangYanke  
	 * @date 2018年7月30日
	 */
	PageInfo<SystemArticle> getList(Map<String, Object> mvm);
	/**
	 * <p>Title:获取文章信息</p>   
	 * @author WangYanke  
	 * @date 2018年7月30日
	 */
	SystemArticle getArticleInfo(Integer id);
	
	/**
	 * <p>Title: 系统文章新增或修改</p>   
	 * @author WangYanke  
	 * @date 2018年7月30日
	 */
	Result addOrEditArticle(SystemArticle systemArticle);
	
	/**
	 * <p>Title: 删除系统文章</p>   
	 * @author WangYanke  
	 * @date 2018年7月30日
	 */
	ResultInfo delArticle(Integer id);
	
	/**
	 * <p>Title: 文章发布/取消发布</p>   
	 * @author WangYanke  
	 * @return 
	 * @date 2018年8月3日
	 */
	ResultInfo articlePublish(Integer isPublish, Integer id);
	
}
