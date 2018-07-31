package com.palmble.dal;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.palmble.entity.ZsGoodsPhotoAlbum;

public interface ZsGoodsPhotoAlbumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZsGoodsPhotoAlbum record);

    int insertSelective(ZsGoodsPhotoAlbum record);

    ZsGoodsPhotoAlbum selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZsGoodsPhotoAlbum record);

    int updateByPrimaryKey(ZsGoodsPhotoAlbum record);

	void insertPotos(ZsGoodsPhotoAlbum goodsPotos);

	void deleteByGoodsId(int goodsId);
    @Select("SELECT * FROM zs_goods_photoalbum WHERE goodsId=#{goodsId}")
	List<ZsGoodsPhotoAlbum> selectPotosByGoodsId(@Param("goodsId")Integer goodsId);
}