package com.palmble.dal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.palmble.entity.ZsGoods;

public interface ZsGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZsGoods record);

    int insertSelective(ZsGoods record);

    ZsGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZsGoods record);

    int updateByPrimaryKeyWithBLOBs(ZsGoods record);

    int updateByPrimaryKey(ZsGoods record);

	List<Map<String, Object>> getGoodsList(@Param("value")String value,@Param("page")Integer page,
			@Param("size")Integer size,@Param("sord")String sord,@Param("isAdminRecom")Integer isAdminRecom,
			@Param("isSale")Integer isSale);

	Integer selectGoodsTotalCount(@Param("value")String value,@Param("isAdminRecom")Integer isAdminRecom,
			@Param("isSale")Integer isSale);
}