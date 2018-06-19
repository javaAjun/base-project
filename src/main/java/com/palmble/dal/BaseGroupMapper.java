package com.palmble.dal;

import org.apache.ibatis.annotations.Mapper;

import com.palmble.entity.BaseGroup;

@Mapper
public interface BaseGroupMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(BaseGroup record);

    int insertSelective(BaseGroup record);

    BaseGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseGroup record);

    int updateByPrimaryKey(BaseGroup record);
}