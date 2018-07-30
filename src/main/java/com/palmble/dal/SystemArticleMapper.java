package com.palmble.dal;

import java.util.List;
import java.util.Map;

import com.palmble.entity.SystemArticle;

public interface SystemArticleMapper {
	List<SystemArticle> getArticleList(Map<String, Object> map);
    int deleteByPrimaryKey(Integer id);

    int insert(SystemArticle record);

    int insertSelective(SystemArticle record);

    SystemArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemArticle record);

    int updateByPrimaryKey(SystemArticle record);
}