package com.palmble.dal;

import com.palmble.entity.ZsGoodsPhotoAlbum;

public interface ZsGoodsPhotoAlbumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZsGoodsPhotoAlbum record);

    int insertSelective(ZsGoodsPhotoAlbum record);

    ZsGoodsPhotoAlbum selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZsGoodsPhotoAlbum record);

    int updateByPrimaryKey(ZsGoodsPhotoAlbum record);
}