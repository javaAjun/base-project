package com.palmble.dal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.palmble.entity.ZsGoodsSku;

public interface ZsGoodsSkuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZsGoodsSku record);

    int insertSelective(ZsGoodsSku record);

    ZsGoodsSku selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZsGoodsSku record);

    int updateByPrimaryKey(ZsGoodsSku record);

	List<Map<String, Object>> selectGoodsSkuList(@Param("goodsCateId")Integer goodsCateId, @Param("skuId")Integer skuId,
			@Param("page")Integer page, @Param("rows")Integer rows,@Param("sord")String sord);

	Integer selectGoodsSkuCount(@Param("goodsCateId")Integer goodsCateId, @Param("skuId")Integer skuId);

	List<String> selectSkuAttrBySkuId(@Param("skId")Integer skId);

	List<Map<String, Object>> selectSkuAllAttrBySkuId(@Param("skId")Integer skId);

	List<Map<String, Object>> selectADDGoodsSkuList(@Param("skuvalueIds")Integer[] skuvalueIds);
}