package com.palmble.dal;

import com.palmble.entity.ZsGoods;

public interface ZsGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZsGoods record);

    int insertSelective(ZsGoods record);

    ZsGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZsGoods record);

    int updateByPrimaryKeyWithBLOBs(ZsGoods record);

    int updateByPrimaryKey(ZsGoods record);
}