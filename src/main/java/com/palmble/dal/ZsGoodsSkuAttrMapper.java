package com.palmble.dal;

import com.palmble.entity.ZsGoodsSkuAttr;

public interface ZsGoodsSkuAttrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZsGoodsSkuAttr record);

    int insertSelective(ZsGoodsSkuAttr record);

    ZsGoodsSkuAttr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZsGoodsSkuAttr record);

    int updateByPrimaryKey(ZsGoodsSkuAttr record);
}