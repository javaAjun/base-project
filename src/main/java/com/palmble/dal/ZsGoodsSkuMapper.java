package com.palmble.dal;

import com.palmble.entity.ZsGoodsSku;

public interface ZsGoodsSkuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZsGoodsSku record);

    int insertSelective(ZsGoodsSku record);

    ZsGoodsSku selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZsGoodsSku record);

    int updateByPrimaryKey(ZsGoodsSku record);
}