package com.palmble.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.palmble.dal.SystemArticleMapper;
import com.palmble.entity.Result;
import com.palmble.entity.SystemArticle;
import com.palmble.service.SystemArticleService;
import com.palmble.utils.ResultInfo;

@Service
public class SystemArcitleServiceImpl implements SystemArticleService {

	@Resource
	private SystemArticleMapper articleMapper;
	
	@Override
	public PageInfo<SystemArticle> getList(Map<String, Object> map) {
		PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("rows").toString()));
		List<SystemArticle> list = articleMapper.getArticleList(map);
		PageInfo<SystemArticle> pageSource=new PageInfo<>(list);
		return pageSource;
	}

	@Override
	public SystemArticle getArticleInfo(Integer id) {
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public Result addOrEditArticle(SystemArticle systemArticle) {
		Result result=new Result<>();
		Integer num=0;
		String url="";
		if(systemArticle.getId()!=null&&!systemArticle.getId().equals("")) {
			num= articleMapper.updateByPrimaryKeySelective(systemArticle);
			url="system_article_edit.html?id="+systemArticle.getId();
		}else {
			articleMapper.insert(systemArticle);
			url="system_article_edit.html";
		}
		
		if(num>0) {
			result.setCode(1);
			result.setMsg("操作成功");
			result.setUrl(url);
		}else {
			result.setCode(0);
			result.setMsg("操作失败");
			result.setUrl(url);
		}
		return result;
	}

	@Override
	public ResultInfo delArticle(Integer id) {
		Integer num=articleMapper.deleteByPrimaryKey(id);
		if(num>0) {
			return new ResultInfo(1, "删除成功");
		}else {
			return new ResultInfo(-1, "删除失败");
		}
	}

}
