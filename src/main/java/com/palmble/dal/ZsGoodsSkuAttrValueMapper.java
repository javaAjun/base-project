package com.palmble.dal;

import java.util.List;

import com.palmble.entity.ZsGoodsSkuAttrValue;

public interface ZsGoodsSkuAttrValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZsGoodsSkuAttrValue record);

    int insertSelective(ZsGoodsSkuAttrValue record);

    ZsGoodsSkuAttrValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZsGoodsSkuAttrValue record);

    int updateByPrimaryKey(ZsGoodsSkuAttrValue record);

	void deleteBySkuId(Integer id);

	List<ZsGoodsSkuAttrValue> selectSKUValuesByskuId(Integer id);
}