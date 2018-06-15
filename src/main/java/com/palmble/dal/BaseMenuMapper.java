package com.palmble.dal;

import org.apache.ibatis.annotations.Mapper;

import com.palmble.entity.BaseMenu;

@Mapper
public interface BaseMenuMapper extends MyMapper<BaseMenuMapper>{
    int deleteByPrimaryKey(Integer id);

    int insert(BaseMenu record);

    int insertSelective(BaseMenu record);

    BaseMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseMenu record);

    int updateByPrimaryKey(BaseMenu record);
}