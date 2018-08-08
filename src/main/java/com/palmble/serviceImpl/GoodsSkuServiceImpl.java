package com.palmble.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.util.StringUtil;
import com.palmble.dal.ZsGoodsSkuAttrMapper;
import com.palmble.dal.ZsGoodsSkuAttrValueMapper;
import com.palmble.dal.ZsGoodsSkuMapper;

import com.palmble.entity.ZsGoodsSkuAttr;
import com.palmble.entity.ZsGoodsSkuAttrValue;
import com.palmble.service.GoodsSkuService;
import com.palmble.utils.ResponsDatas;
import com.palmble.utils.SysConstant;
/**
 * 商品规格业务类
 * @author malingbing
 *
 */
@Service
public class GoodsSkuServiceImpl implements GoodsSkuService {

	@Autowired
	private ZsGoodsSkuMapper skuMapper;
	@Autowired
	private ZsGoodsSkuAttrMapper skuAttrMapper;
	@Autowired
	private ZsGoodsSkuAttrValueMapper skuAttrValueMapper;
	
	@Override
	public ResponsDatas<?> getGoodsSkuList(Integer page, Integer rows, String sord, Integer goodsCateId, Integer skuId){
		
		try {
			List<Map<String,Object>> dataMap= skuMapper.selectGoodsSkuList(goodsCateId,skuId,page!=null?(page-1)*rows:null,rows,sord);
			Integer totalCount=skuMapper.selectGoodsSkuCount(goodsCateId,skuId);
			
			for (Map<String,Object> map: dataMap) {
				Integer skId=Integer.valueOf(map.get("id").toString());
				List<String> attrValue=skuMapper.selectSkuAttrBySkuId(skId);
				String arvalue="";
				if(!attrValue.isEmpty()&&attrValue!=null) {
					 arvalue=String.join(",", attrValue);
				}
				map.put("skuValue", arvalue);
			}
			if(page!=null&&rows!=null) {
				return ResponsDatas.success(dataMap, totalCount.longValue(), page, rows);
			}else {
				return  ResponsDatas.success(dataMap);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponsDatas.fail(e.getMessage(), null);
		}
		
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResponsDatas<?> operGoodsSku(ZsGoodsSkuAttr goodsSku) {
		try {
			if(goodsSku.getOper().equals(SysConstant.OPER_ADD)||goodsSku.getOper().equals(SysConstant.OPER_EDIT)) {
				if(goodsSku.getCateId()==null) {
					return ResponsDatas.fail("请选择分类", null);
				}
				if(StringUtil.isEmpty(goodsSku.getAttrName())) {
					return ResponsDatas.fail("规格名称不能为空", null);
				}
				if(goodsSku.getAttrValue()==null) {
					return ResponsDatas.fail("请添加规格值", null);
				}
			}
			if(goodsSku.getOper().equals(SysConstant.OPER_ADD)) {
				   skuAttrMapper.insertSelective(goodsSku);
				   String[] attrValues=goodsSku.getAttrValue();
					for (String attrValue : attrValues) {
						ZsGoodsSkuAttrValue  attr=new ZsGoodsSkuAttrValue(attrValue,goodsSku.getId());
						skuAttrValueMapper.insertSelective(attr);
					}
			}else if(goodsSku.getOper().equals(SysConstant.OPER_EDIT)) {
				 skuAttrMapper.updateByPrimaryKeySelective(goodsSku);
				 String[] attrValues=goodsSku.getAttrValue();
				 skuAttrValueMapper.deleteBySkuId(goodsSku.getId());
				 for (String attrValue : attrValues) {
						ZsGoodsSkuAttrValue  attr=new ZsGoodsSkuAttrValue(attrValue,goodsSku.getId());
						skuAttrValueMapper.insertSelective(attr);
					}
			}else if(goodsSku.getOper().equals(SysConstant.OPER_DEL)) {
				 skuAttrValueMapper.deleteBySkuId(goodsSku.getId());
				 skuAttrMapper.deleteByPrimaryKey(goodsSku.getId());
			}
			return ResponsDatas.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponsDatas.fail(e.getMessage(), null);
		}
	}
	@Override
	public ResponsDatas<?> getGoodsSkuId(Integer id) {
		try {
			ZsGoodsSkuAttr attr=skuAttrMapper.selectByPrimaryKey(id);
			List<ZsGoodsSkuAttrValue> attrS=skuAttrValueMapper.selectSKUValuesByskuId(id);
			attr.setSkuValues(attrS);
			return  ResponsDatas.success(attr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponsDatas.fail(e.getMessage(), null);
		}
	}
	@Override
	public ResponsDatas<?> getGoodsSkuList(Integer[] skuvalueIds,Integer[] skuIds ) {
		try {
			if(null!=skuIds&&null!=skuvalueIds) {//商品添加选择的规格
				 List<Map<String,Object>> dataMap= skuMapper.selectADDGoodsSkuList(skuvalueIds);
				for (Map<String, Object> map : dataMap) {
					
				}
				
			}
		  List<Map<String,Object>> dataMap= skuMapper.selectGoodsSkuList(null,null,null,null,null);
		  for (Map<String,Object> map: dataMap) {
			Integer skId=Integer.valueOf(map.get("id").toString());
			List<Map<String,Object>> attrValue=skuMapper.selectSkuAllAttrBySkuId(skId);
			
			map.put("skuValue", attrValue.isEmpty()||attrValue==null?null:attrValue);
		}
		return  ResponsDatas.success(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponsDatas.fail(e.getMessage(), null);
		}
		
	}

}
