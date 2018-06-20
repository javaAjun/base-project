package com.palmble.dal;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.palmble.entity.BaseMenu;

@Mapper
public interface BaseMenuMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(BaseMenu record);

    int insertSelective(BaseMenu record);

    BaseMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseMenu record);

    int updateByPrimaryKey(BaseMenu record);
    List<BaseMenu> byAllMenuList(Map<String, String> map);
}