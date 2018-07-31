package com.palmble.dal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

	@Select(" SELECT zg.*, zc.catName,  zc.catSort FROM zs_goods zg LEFT JOIN zs_goods_category zc ON zg.goodsCateId = zc.id  " + 
			"		WHERE zg.id=#{goodsId}")
	Map<String, Object> getGoodsById(@Param("goodsId")Integer goodsId);
}