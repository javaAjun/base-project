package com.palmble.dal;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.palmble.entity.ZsGoodsCategory;

public interface ZsGoodsCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZsGoodsCategory record);

    int insertSelective(ZsGoodsCategory record);

    ZsGoodsCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZsGoodsCategory record);

    int updateByPrimaryKey(ZsGoodsCategory record);

    //获取顶级菜单
    @Select("SELECT * FROM zs_goods_category WHERE parentId=0")
    List<ZsGoodsCategory> getToplevel();
   //子菜单
    
	List<ZsGoodsCategory> getChildlevel(@Param("pid")Integer pid,@Param("goodsGateId")Integer goodsGateId);
}