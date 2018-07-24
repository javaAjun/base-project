package com.palmble.dal;

import com.palmble.entity.ZsGoodsCategory;

public interface ZsGoodsCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZsGoodsCategory record);

    int insertSelective(ZsGoodsCategory record);

    ZsGoodsCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZsGoodsCategory record);

    int updateByPrimaryKey(ZsGoodsCategory record);
}