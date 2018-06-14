package com.palmble.dal;

import com.palmble.entity.BaseGroup;

public interface BaseGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseGroup record);

    int insertSelective(BaseGroup record);

    BaseGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseGroup record);

    int updateByPrimaryKey(BaseGroup record);
}