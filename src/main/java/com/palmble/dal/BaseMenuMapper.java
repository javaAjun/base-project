package com.palmble.dal;

import com.palmble.entity.BaseMenu;

public interface BaseMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseMenu record);

    int insertSelective(BaseMenu record);

    BaseMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseMenu record);

    int updateByPrimaryKey(BaseMenu record);
}