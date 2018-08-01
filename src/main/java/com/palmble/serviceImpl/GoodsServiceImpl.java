package com.palmble.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.palmble.dal.ZsGoodsCategoryMapper;
import com.palmble.dal.ZsGoodsMapper;
import com.palmble.dal.ZsGoodsPhotoAlbumMapper;
import com.palmble.entity.ZsGoods;
import com.palmble.entity.ZsGoodsCategory;
import com.palmble.entity.ZsGoodsPhotoAlbum;
import com.palmble.service.GoodsService;
import com.palmble.utils.DateUtil;
import com.palmble.utils.FileTypeUtils;
import com.palmble.utils.GoodsCateLev;
import com.palmble.utils.PageInfoUtil;
import com.palmble.utils.ResponsDatas;
import com.palmble.utils.SysConstant;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;
@Service
public class GoodsServiceImpl implements GoodsService{
  @Autowired
  private ZsGoodsMapper goodsMapper;
  @Autowired
  private ZsGoodsPhotoAlbumMapper potosMapper;
  @Autowired
  private ZsGoodsCategoryMapper goodsCateMapper;
  
  @Value("${image.location}")
  private String filePath;
  @Value("${imgShow.url}")
  private String ImgHttpTop;
  
  
	@Override
	public ResponsDatas getGoodsList(String value,Integer page,Integer size,String sord,Integer isAdminRecom,Integer isSale) {
		if(page==null)page=1;
		if(size==null)size=10;
		
		List<Map<String,Object>> dataList=new ArrayList<Map<String,Object>>();
		try {
			dataList=goodsMapper.getGoodsList(value,(page-1)*size,size,sord,isAdminRecom,isSale);
			Integer totalCount=goodsMapper.selectGoodsTotalCount(value,isAdminRecom,isSale);
			return ResponsDatas.success(dataList, totalCount.longValue(), page, size);
		} catch (Exception e) {
			e.printStackTrace();
			return   ResponsDatas.fail(e.getMessage(),dataList);
		}
	}

	/**
	 * 编辑商品的信息
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResponsDatas operGoodsInfo(ZsGoods goods) {
		try {
			if(goods.getOper().equals(SysConstant.OPER_ADD)) {//添加
				ResponsDatas data=this.IsNullGoodsParam(goods);
				if(!data.getStatus().equals("200")) {
					return data;
				}else {
					goods=(ZsGoods) data.getData();
					goodsMapper.insertSelective(goods);
					Integer goodsId=goods.getId();
					if(goods.getGoodsCoverImgs().length>0) {
						String[] imgs=goods.getGoodsCoverImgs();
					  for (String goodsImg : imgs) {
						  ZsGoodsPhotoAlbum goodsPotos=new ZsGoodsPhotoAlbum(goodsImg, goodsId);
						  //商品相册
						  potosMapper.insertSelective(goodsPotos);
//						  potosMapper.insertPotos(goodsPotos);//此方法暂未使用
					}
					}
					
				}
				
			}else if(goods.getOper().equals(SysConstant.OPER_EDIT)) {//修改
				ResponsDatas data=this.IsNullGoodsParam(goods);
				if(!data.getStatus().equals("200")) {
					return data;
				}else {
					goods=(ZsGoods) data.getData();
					goodsMapper.updateByPrimaryKeySelective(goods);
					int goodsId=goods.getId();
					if(goods.getGoodsCoverImgs()!=null) {
						String[] imgs=goods.getGoodsCoverImgs();
						potosMapper.deleteByGoodsId(goodsId);
					  for (String goodsImg : imgs) {
						  ZsGoodsPhotoAlbum goodsPotos=new ZsGoodsPhotoAlbum(goodsImg, goodsId);
						  //商品相册
						  potosMapper.insertSelective(goodsPotos);
						  
//						  potosMapper.insertPotos(goodsPotos);//此方法暂未使用
				     	}
					}
				}
			}else if(goods.getOper().equals("isSale")){
				goodsMapper.updateByPrimaryKeySelective(goods);
			}else if(goods.getOper().equals("isAdminRecom")){
				goodsMapper.updateByPrimaryKeySelective(goods);
			} if(goods.getOper().equals(SysConstant.OPER_DEL)) {
				Integer id=goods.getId();
				Integer goodsId=goods.getId();
				potosMapper.deleteByGoodsId(goodsId);				
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
		return ResponsDatas.success();
	}

	@Override
	public ResponsDatas upLoadImg(MultipartFile[] files) {
		try {
			String fileName=DateUtil.getNOWDate();
		if (files != null && files.length >0) {
			List<String> pathList=new ArrayList<String>();
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

	@Override
	public ResponsDatas IsNullGoodsParam(ZsGoods goods) {
		if(StringUtil.isEmpty(goods.getGoodsNo())) {//如果编号为空
			return  ResponsDatas.fail("商品编号不能为空!", null);
		}else if(goods.getOper().equals(SysConstant.OPER_ADD)){
			int num=goodsMapper.selectGoodsInfoByGoodsNo(goods);
			if(num>=1) {
				return  ResponsDatas.fail("商品编号重复!", null);
			}
		}
		if(StringUtil.isEmpty(goods.getGoodsCoverImg())) {//封面图片为空
			return  ResponsDatas.fail("封面图片为空!", null);
		}
		if(goods.getMarketPrice().equals(BigDecimal.ZERO)) {
			return  ResponsDatas.fail("市场价必须大于0!", null);
		}
		if(goods.getShopPrice().equals(BigDecimal.ZERO)) {
			return  ResponsDatas.fail("店铺价必须大于0!", null);
		}
		if(StringUtil.isEmpty(goods.getGoodsSpec())) {
			return  ResponsDatas.fail("规格不能为空！", null);
		}
		if(StringUtil.isEmpty(goods.getGoodsDesc())) {
			return  ResponsDatas.fail("商品描述不能为空！", null);
		}
		if(StringUtils.isEmpty(goods.getGoodsContent())){
			return  ResponsDatas.fail("商品详情不能为空！", null);
		}
		String createTime=DateUtil.getCurrentDateTime();//创建时间
		goods.setCreateTime(DateUtil.stringToDateTime(createTime));
		boolean isSale=goods.getIsSale();
		if(isSale) {//上架时间
			String saletime=DateUtil.getCurrentDateTime();
			goods.setSaleTime(DateUtil.stringToDateTime(saletime));
		}else {//下架时间
			String unsaletime=DateUtil.getCurrentDateTime();
			goods.setSaleTime(DateUtil.stringToDateTime(unsaletime));
		}
		if(goods.getGoodsCateId()==null) {
			return  ResponsDatas.fail("请选择分类！", null);
		}
		return ResponsDatas.success(goods);
	}

	@Override
	public ResponsDatas getGoodsById(Integer goodsId) {
		Map<String,Object> dataMap=new  HashMap<String,Object>();
		
			try {
				dataMap=goodsMapper.getGoodsById(goodsId);
				
					if(dataMap!=null) {
						List<ZsGoodsPhotoAlbum> goodslist=potosMapper.selectPotosByGoodsId(goodsId);
						dataMap.put("potos", goodslist==null?null:goodslist);
//						dataList.add(map);
					return ResponsDatas.success(dataMap);
				}else {
					return  ResponsDatas.fail("商品为空！", null);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return  ResponsDatas.fail(e.getMessage(), null);
			}
		
	}

	@Override
	public ResponsDatas getGoodsCategoryInfo(Integer goodsId) {
		try {
			Integer goodsGateId=null;
			if(goodsId!=0) {
				Map<String,Object> dataMap=dataMap=goodsMapper.getGoodsById(goodsId);
				if(dataMap!=null) {
					goodsGateId=Integer.valueOf(dataMap.get("goodsCateId").toString());
				}
			}
			List<ZsGoodsCategory> goodsCate=goodsCateMapper.getToplevel();//获取顶级菜单
			
			for (ZsGoodsCategory zct : goodsCate) {
				//获取子菜单
				Integer pid=zct.getId();
				List<ZsGoodsCategory> childCate= goodsCateMapper.getChildlevel(pid,goodsGateId);
				zct.setChildLevel(childCate);
			}
			return ResponsDatas.success(goodsCate);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponsDatas.fail(e.getMessage());
		}
		
		
	}

//	@Override
//	public ResponsDatas<List<Map<String,Object>>> getPageGoodsInfo(Integer page, Integer rows,Integer pid, String value) {
//		if(page==null)page=1;
//		if(rows==null)rows=10;
//		page=(page-1)*rows;
//		try{
//		List<Map<String,Object>> goodsCateInfo=goodsCateMapper.getPageGoodsCateInfo(page,rows,value,pid);
//		Integer totalCount=goodsCateMapper.getTotalCountCateInfo(value,pid);
//		return ResponsDatas.success(goodsCateInfo, totalCount.longValue(), page+1, rows);
//	} catch (Exception e) {
//		e.printStackTrace();
//		return   ResponsDatas.fail(e.getMessage());
//	}
//	}
	@Override
	public ResponsDatas<List<ZsGoodsCategory>> getPageGoodsInfo(Integer page, Integer rows,Integer pid, String value) {
		if(page!=null&&rows!=null)	page=(page-1)*rows;
	
	
		try{
			List<ZsGoodsCategory> goodsCateInfo=goodsCateMapper.getPageGoodsCateInfo(page,rows,value,pid);
			
		   Integer totalCount=goodsCateMapper.getTotalCountCateInfo(value,pid);
		   List<GoodsCateLev> backList=new ArrayList<GoodsCateLev>();
		   int id=0; int lev=0;
		   if(pid!=null) {
			   id=pid;
			   lev=1;
		   }
		   recMessageType(backList, goodsCateInfo, id, lev);
		   for (GoodsCateLev goodsCateLev : backList) {
			   goodsCateLev.setCatName(setNBSP(goodsCateLev)
	                    + goodsCateLev.getCatName());
		}
		return ResponsDatas.success(backList, totalCount.longValue(), page+1, rows);
	} catch (Exception e) {
		e.printStackTrace();
		return   ResponsDatas.fail(e.getMessage());
	}
	}
	/**
     * 根据层级的关系处理 类别名称显示
     * @param messageType
     * @return
     */
    private String setNBSP(GoodsCateLev messageType) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < messageType.getLev(); i++) {
            sb.append("&nbsp;&nbsp;&nbsp;|--");
        }
        return sb.toString();
    }
    /**
     * 对无限分类集合 数组进行排序
     * 
     * @param mlist 要排序的集合
     * @param id    父id 为0开始 
     * @param lev 层数 为了显示缩进 而添加的
     */
    public void recMessageType(List<GoodsCateLev> bList,
        List<ZsGoodsCategory> mlist, int id, int lev) {
        for (ZsGoodsCategory messageType : mlist) {
            if (messageType.getParentId() == id) {
            	GoodsCateLev messageTypeLev = new GoodsCateLev();
            /*为了显示层数 而 又封装了一个MessageTypeLev 类型，而且在继承原来MessageType 还做了一下转换 [messageTypeLev.from(messageType);
     ]此处方法觉得有点 小小的不可取 ，以后解决 ，目前先解决问题再说 */
                messageTypeLev.from(messageType);
                messageTypeLev.setLev(lev);
                bList.add(messageTypeLev);
                recMessageType(bList, mlist, messageType.getId(), lev + 1);
            }
        }
    }
	@Override
	public ResponsDatas<List<ZsGoodsCategory>> getPageGoodsTopLevel(Integer pid,String value) {
		try{
//		List<ZsGoodsCategory> goodsCateInfo=goodsCateMapper.getPageGoodsTopLevel(pid,value);
			List<ZsGoodsCategory> goodsCateInfo=goodsCateMapper.getPageGoodsCateInfo(null,null,value,pid);
		 List<GoodsCateLev> backList=new ArrayList<GoodsCateLev>();
		   recMessageType(backList, goodsCateInfo, 0, 0);
		   for (GoodsCateLev goodsCateLev : backList) {
			   goodsCateLev.setCatName(setNBSP(goodsCateLev)
	                    + goodsCateLev.getCatName());
		}
		
		return ResponsDatas.success(backList);
		} catch (Exception e) {
			e.printStackTrace();
			return   ResponsDatas.fail(e.getMessage());
		}
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResponsDatas operGoodsCateInfo(ZsGoodsCategory goods) {
		try {
			if(goods.getOper().equals(SysConstant.OPER_ADD)) {
				if(StringUtil.isEmpty(goods.getCatName())) {
					 return ResponsDatas.fail(SysConstant.ERROR_CATE, null);
				}
				goodsCateMapper.insertSelective(goods);
			}else if(goods.getOper().equals(SysConstant.OPER_EDIT)){
				if(StringUtil.isEmpty(goods.getCatName())) {
					 return ResponsDatas.fail(SysConstant.ERROR_CATE, null);
				}
				goodsCateMapper.updateByPrimaryKeySelective(goods);
				
			}else if(goods.getOper().equals(SysConstant.OPER_DEL)) {//删除
				Integer pid=goods.getId();
				List<ZsGoodsCategory> cateList=goodsCateMapper.getPageGoodsCateInfo(null,null,null,pid);
				if(!cateList.isEmpty()&&cateList!=null) {
					return ResponsDatas.fail("有子类无法删除", null);
				}
				Integer cateId=goods.getId();
				List<ZsGoods> gdL=goodsCateMapper.selectGoodsByCateId(cateId); 
				if(!gdL.isEmpty()&&gdL!=null) {
					return ResponsDatas.fail("该类有商品删除失败!", null);
				}
				
				Integer id=goods.getId();
				ZsGoodsCategory zc=goodsCateMapper.selectByPrimaryKey(id);
				String imgurl=zc.getGoodsCateImg();
				if(StringUtil.isNotEmpty(imgurl)) {
					this.delImgFile(imgurl);//删除硬盘文件
				}
				goodsCateMapper.deleteByPrimaryKey(id);
				
			}else if(goods.getOper().equals(SysConstant.OPER_STATE)){
				goodsCateMapper.updateByPrimaryKeySelective(goods);
			}else if(goods.getOper().equals(SysConstant.OPER_SORT)){
				goodsCateMapper.updateByPrimaryKeySelective(goods);
			}else{
				return ResponsDatas.fail("oper参数不对", null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponsDatas.fail(e.getMessage(), null);
		}
		return ResponsDatas.success();
	}

	@Override
	public ResponsDatas operGoodsCateInfoById(Integer id) {
		try {
			ZsGoodsCategory goodsCate=goodsCateMapper.selectByPrimaryKey(id);
			return ResponsDatas.success(goodsCate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponsDatas.fail(e.getMessage(), null);
		}
	
		
	
	}

	@Override
	public void delImgFile(String path) {
		String fileName=path.substring(path.lastIndexOf("\\")+1);
		String name=path.substring(15, path.lastIndexOf("\\")+1);
		String Url=filePath+fileName+"/"+name;
		boolean flag=FileTypeUtils.deleteFile(Url);
		if(!flag) {
			FileTypeUtils.deleteFile(Url);
		}
		
	}
}
