package com.palmble.dal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.palmble.entity.ZsGoods;
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

	//获取分类
	//List<Map<String,Object>> getPageGoodsCateInfo(@Param("page")Integer page,@Param("rows") Integer rows,@Param("value") String value,@Param("pid")Integer pid);
	List<ZsGoodsCategory> getPageGoodsCateInfo(@Param("page")Integer page,@Param("rows") Integer rows,@Param("value") String value,@Param("pid")Integer pid);
	//获取分类的总数量
	Integer getTotalCountCateInfo(@Param("value")String value,@Param("pid")Integer pid);

	List<ZsGoodsCategory> getPageGoodsTopLevel(@Param("pid")Integer pid,@Param("value") String value);
     @Select("SELECT * FROM zs_goods WHERE goodsCateId=#{cateId}")
	List<ZsGoods> selectGoodsByCateId(@Param("cateId")Integer cateId);

	ZsGoodsCategory selectParentGoodCateByCateId(Integer id);

	List<ZsGoodsCategory> getGoodsCateLevel(@Param("value") String value,@Param("pid")Integer pid);
}