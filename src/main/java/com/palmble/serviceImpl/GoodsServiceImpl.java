package com.palmble.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.palmble.dal.ZsGoodsMapper;
import com.palmble.service.GoodsService;
import com.palmble.utils.PageInfoUtil;
import com.palmble.utils.ResponsDatas;
@Service
public class GoodsServiceImpl implements GoodsService{
  @Autowired
  private ZsGoodsMapper goodsMapper;

	@Override
	public ResponsDatas getGoodsList(String value,Integer page,Integer size) {
//		ResponsDatas  reponseData=new ResponsDatas();
		if(page==null)page=1;
		if(size==null)size=10;
		page=(page-1)*size;
		try {
			List<Map<String,Object>> dataList=goodsMapper.getGoodsList(value,page,size);
			Integer totalCount=goodsMapper.selectGoodsTotalCount(value);
			PageInfoUtil pageData=new PageInfoUtil(totalCount.longValue(), page+1, size);
			ResponsDatas reponseData = new ResponsDatas("true", "获取数据成功", dataList, pageData);
			return reponseData;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResponsDatas reponseData =new ResponsDatas<>("false", "获取数据失败"+e.getMessage());
			return reponseData;
		}
	}
}
