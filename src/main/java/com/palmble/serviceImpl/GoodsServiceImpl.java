package com.palmble.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.palmble.dal.ZsGoodsMapper;
import com.palmble.entity.ZsGoods;
import com.palmble.service.GoodsService;
import com.palmble.utils.DateUtil;
import com.palmble.utils.FileTypeUtils;
import com.palmble.utils.PageInfoUtil;
import com.palmble.utils.ResponsDatas;
import com.palmble.utils.SysConstant;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;
@Service
public class GoodsServiceImpl implements GoodsService{
  @Autowired
  private ZsGoodsMapper goodsMapper;
  @Value("${image.location}")
  private String filePath;
  @Value("${imgShow.url}")
  private String ImgHttpTop;
  
  
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
			e.printStackTrace();
			return ResponsDatas.fail(e.getMessage(), null);
		}
		return null;
	}

	@Override
	public ResponsDatas upLoadImg(MultipartFile[] files) {
		try {
		if (files != null && files.length >0) {
			List<String> pathList=new ArrayList<String>();
			String fileName=DateUtil.getNOWDate();
				File dir = new File(filePath +File.separator + fileName);
				if (!dir.exists()) {	dir.mkdirs();	}
				for (int i = 0; i < files.length; i++) {
					String path=dir.getPath()+File.separator+files[i].getOriginalFilename();
					File fileImg=new File(dir, files[i].getOriginalFilename());
					files[i].transferTo(fileImg);
					String url=ImgHttpTop+File.separator+files[i].getOriginalFilename()+File.separator+fileName;
					pathList.add(url);
				}
				return ResponsDatas.success("上传成功", pathList);
		}else {
			ResponsDatas.fail();
		}
		} catch (IllegalStateException | IOException e) {
			
			e.printStackTrace();
			ResponsDatas.fail(e.getMessage(), null);
		}
		return null;
	}
}
