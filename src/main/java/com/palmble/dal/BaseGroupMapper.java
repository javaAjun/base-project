package com.palmble.dal;

import com.palmble.entity.BaseGroup;

import tk.mybatis.mapper.common.Mapper;

public interface BaseGroupMapper extends Mapper<BaseGroupMapper>{
    int deleteByPrimaryKey(Integer id);

    int insert(BaseGroup record);

    int insertSelective(BaseGroup record);

    BaseGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseGroup record);

    int updateByPrimaryKey(BaseGroup record);
}