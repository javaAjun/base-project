package com.palmble.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.palmble.dal.ZsGoodsMapper;
import com.palmble.entity.ZsGoods;
import com.palmble.service.GoodsService;
import com.palmble.utils.PageInfoUtil;
import com.palmble.utils.ResponsDatas;
import com.palmble.utils.SysConstant;
@Service
public class GoodsServiceImpl implements GoodsService{
  @Autowired
  private ZsGoodsMapper goodsMapper;

	@Override
	public ResponsDatas getGoodsList(String value,Integer page,Integer size,String sord,Integer isAdminRecom,Integer isSale) {
		if(page==null)page=1;
		if(size==null)size=10;
		page=(page-1)*size;
		List<Map<String,Object>> dataList=new ArrayList<Map<String,Object>>();
		try {
			dataList=goodsMapper.getGoodsList(value,page,size,sord,isAdminRecom,isSale);
			Integer totalCount=goodsMapper.selectGoodsTotalCount(value,isAdminRecom,isSale);
			return ResponsDatas.success(dataList, totalCount.longValue(), page+1, size);
		} catch (Exception e) {
			e.printStackTrace();
			return   ResponsDatas.fail(e.getMessage(),dataList);
		}
	}

	/**
	 * 编辑商品的信息
	 */
	@Override
	public ResponsDatas operGoodsInfo(ZsGoods goods) {
		try {
			if(goods.getOper().equals(SysConstant.OPER_ADD)) {//添加
				
			}else if(goods.getOper().equals(SysConstant.OPER_EDIT)) {//修改
				
				int num =goodsMapper.updateByPrimaryKeySelective(goods);
				if(num>0) {
					return ResponsDatas.success();
				}else {
					return ResponsDatas.fail();
				}
			}else if(goods.getOper().equals(SysConstant.OPER_DEL)) {
				Integer id=goods.getId();
				int num =goodsMapper.deleteByPrimaryKey(id);
				if(num>0) {
					return ResponsDatas.success();
				}else {
					return ResponsDatas.fail();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponsDatas.fail(e.getMessage(), null);
		}
		return null;
	}
}
