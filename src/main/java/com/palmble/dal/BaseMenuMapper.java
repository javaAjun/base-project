package com.palmble.dal;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.Page;
import com.palmble.entity.BaseMenu;

@Mapper
public interface BaseMenuMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(BaseMenu record);

    int insertSelective(BaseMenu record);

    BaseMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseMenu record);

    int updateByPrimaryKey(BaseMenu record);
    Page<Object> byAllMenuList(Map<String, String> map);
}