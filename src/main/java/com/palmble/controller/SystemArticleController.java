package com.palmble.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.palmble.base.PalmbleBaseController;
import com.palmble.entity.Result;
import com.palmble.entity.SystemArticle;
import com.palmble.service.SystemArticleService;
import com.palmble.utils.ResultInfo;
/**
* <p>Title: 系统文章Controller</p>  
* <p>Description:pamable </p>  
* @author WangYanke  
* @date 2018年7月30日 
* @version 1.0
 */
@RestController
@RequestMapping("/article")
public class SystemArticleController extends PalmbleBaseController{
	@Autowired
	private SystemArticleService systemArticleService;
	
	@RequestMapping("/list")
	public PageInfo<SystemArticle> getArticleList(@RequestParam Map<String, Object> mvm) {
		return systemArticleService.getList(mvm);
	}
	
	@RequestMapping("/articleInfo")
	public SystemArticle getArticleInfo(Integer id) {
		return systemArticleService.getArticleInfo(id);
	}
	
	@RequestMapping("/addOrEditArticle")
	public Result addOrEditArticle(SystemArticle systemArticle) {
		return systemArticleService.addOrEditArticle(systemArticle);
	}
	
	@RequestMapping("/delArticle")
	public ResultInfo delArticle(Integer id) {
		return systemArticleService.delArticle(id);
	}
	
	@RequestMapping("/publish")
	public ResultInfo articlePublish(@RequestParam Integer isPublish,Integer id) {
		return systemArticleService.articlePublish(isPublish,id);

	}
	
}
