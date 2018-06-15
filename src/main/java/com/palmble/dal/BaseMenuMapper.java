package com.palmble.dal;

import com.palmble.entity.BaseMenu;

import tk.mybatis.mapper.common.Mapper;

public interface BaseMenuMapper extends Mapper<BaseMenuMapper>{
    int deleteByPrimaryKey(Integer id);

    int insert(BaseMenu record);

    int insertSelective(BaseMenu record);

    BaseMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseMenu record);

    int updateByPrimaryKey(BaseMenu record);
}