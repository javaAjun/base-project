package com.palmble.service;

import com.github.pagehelper.PageInfo;
import com.palmble.utils.ResponsDatas;

/**
 * 
 * @author malingbing
 *
 */
public interface GoodsService {

	public ResponsDatas getGoodsList(String value,Integer page,Integer size);
}
