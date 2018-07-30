package com.palmble.dal;

import com.palmble.entity.SystemArticle;

public interface SystemArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemArticle record);

    int insertSelective(SystemArticle record);

    SystemArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemArticle record);

    int updateByPrimaryKeyWithBLOBs(SystemArticle record);

    int updateByPrimaryKey(SystemArticle record);
}