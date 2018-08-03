package com.palmble.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmble.dal.ZsGoodsSkuMapper;
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

}
