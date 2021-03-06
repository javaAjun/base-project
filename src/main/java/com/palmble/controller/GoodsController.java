package com.palmble.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.palmble.entity.ZsGoods;
import com.palmble.entity.ZsGoodsCategory;
import com.palmble.service.GoodsService;
import com.palmble.ueditor.ActionEnter;
import com.palmble.utils.FileTypeUtils;
import com.palmble.utils.ResponsDatas;
/**
 * 商品信息 控制器类
 * @author malingbing
 *
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
     private GoodsService goodsService;
    
    @Value("${image.location}")
    private String filePath;
	//根据 关键字段 获取商品的列表
    /**
     * 
     * @param value 关键字
     * @param page
     * @param size
     * goodsCateId  分类
     * goodsId  商品
     * @return
     */
	@RequestMapping("/getGoodsList")
	public ResponsDatas getGoodsInfo(@RequestParam(required=false) String value,Integer page,
			Integer rows,String sord,Integer goodsCateId,Integer goodsId,
			@RequestParam(required=false)Integer isAdminRecom,
			@RequestParam(required=false)Integer isSale) {
		ResponsDatas response = goodsService.getGoodsList(value, page, rows,sord,isAdminRecom,isSale,goodsCateId,goodsId);
		return response;
	}
	
	/**
	 * 根据id获取商品的信息
	 * @param goodsId
	 * @return
	 */
	@RequestMapping("/getGoodsById")
	public ResponsDatas getGoodsInfo(Integer goodsId) {
		ResponsDatas response = goodsService.getGoodsById(goodsId);
		return response;
	}
	/**
	 * 获取分类
	 * @param goodsId
	 * @return
	 */
	@RequestMapping("/getGoodsCategoryInfo")
	public ResponsDatas getGoodsCategoryInfo(Integer goodsId) {
		ResponsDatas response = goodsService.getGoodsCategoryInfo(goodsId);
		return response;
	}
	/**
	 * 编辑商品
	 * @param goods
	 * @return
	 */
	@RequestMapping("/operGoodsInfo")
	public ResponsDatas operGoodsInfo(HttpServletRequest request,ZsGoods goods) {
//		String[] imgs = request.getParameterValues("imgs");
//		if(imgs.length>0) {
//			goods.setGoodsCoverImgs(imgs);
//		}
		ResponsDatas response=goodsService.operGoodsInfo(goods);
		return response;
		
	}
	/**
	 * 上传商品图片
	 * @param files
	 * @return
	 */
	@RequestMapping("/uploadImg")
	public ResponsDatas<?> operGoodsInfo(HttpServletRequest request, HttpServletResponse response ,@RequestParam("file")MultipartFile[] files) {
		
			ResponsDatas<?> responseData=goodsService.upLoadImg(files);
			return responseData;
	}
	@RequestMapping(value="/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
	/**
	 * 显示图片
	 * @param url
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/showImg/{name}/{fileName}")
	public void operGoodsInfo(HttpServletResponse response,@PathVariable String fileName, @PathVariable  String name) throws IOException {
		String Url=filePath+fileName+"/"+name;
		File filePic = new File(Url);
		 FileInputStream fis;
	        fis = new FileInputStream(filePic);
	        long size = filePic.length();
	        byte[] temp = new byte[(int) size];
	        fis.read(temp, 0, (int) size);
	        fis.close();
	        byte[] data = temp;
	        response.setContentType("image/png");
	        OutputStream out = response.getOutputStream();
	        out.write(data);
	        out.flush();
	        out.close();
	}
	/**
	 * 删除
	 * @param url
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/deleteFile")
	public void deleteFile(HttpServletResponse response,String path) throws IOException {
		goodsService.delImgFile(path);

		
	}
	/**
	 * 获取商品分类
	 * @param response
	 * @param page
	 * @param rows
	 * @param value
	 * @throws IOException
	 */
	@RequestMapping("/getGoodsCateInfo")
	public ResponsDatas getGoodsCateInfo(HttpServletResponse response,Integer page,Integer rows,Integer id,String value) throws IOException {
		ResponsDatas result= goodsService.getPageGoodsInfo(page,rows,id,value);
		return result;
	}
	/**
	 * 获取商品分类顶级菜单 下拉框
	 * @param response
	 * @param page
	 * @param rows
	 * @param value
	 * @throws IOException
	 */
	@RequestMapping("/getGoodsCateTopLevel")
	public ResponsDatas getGoodsCateInfo(HttpServletResponse response,Integer  id,String value) throws IOException {
		ResponsDatas result= goodsService.getPageGoodsTopLevel(id,value);
		return result;
	}
	/**
	 * 编辑商品分类
	 * @param goods
	 * @return
	 */
	@RequestMapping("/operGoodsCateInfo")
	public ResponsDatas operGoodsCateInfo(HttpServletRequest request,ZsGoodsCategory goods) {
		ResponsDatas response=goodsService.operGoodsCateInfo(goods);
		return response;
		
	}
	/**
	 * 根据id获取分类的信息
	 * @param goods
	 * @return
	 */
	@RequestMapping("/operGoodsCateInfoByid")
	public ResponsDatas operGoodsCateInfo(HttpServletRequest request,Integer id) {
		ResponsDatas response=goodsService.operGoodsCateInfoById(id);
		return response;
		
	}
}
