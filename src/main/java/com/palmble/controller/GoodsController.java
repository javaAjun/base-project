package com.palmble.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.palmble.entity.ZsGoods;
import com.palmble.service.GoodsService;
import com.palmble.utils.FileTypeUtils;
import com.palmble.utils.ResponsDatas;
import com.palmble.utils.SysConstant;
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
     * @return
     */
	@RequestMapping("/getGoodsList")
	public ResponsDatas getGoodsInfo(@RequestParam(required=false) String value,Integer page,
			Integer rows,String sord,
			@RequestParam(required=false)Integer isAdminRecom,
			@RequestParam(required=false)Integer isSale) {
		ResponsDatas response = goodsService.getGoodsList(value, page, rows,sord,isAdminRecom,isSale);
		return response;
	}
	/**
	 * 编辑商品
	 * @param goods
	 * @return
	 */
	@RequestMapping("/operGoodsInfo")
	public ResponsDatas operGoodsInfo(ZsGoods goods) {
		ResponsDatas response=goodsService.operGoodsInfo(goods);
		return response;
		
	}
	/**
	 * 上传商品图片
	 * @param files
	 * @return
	 */
	@RequestMapping("/uploadImg")
	public ResponsDatas<?> operGoodsInfo(@RequestParam("file")MultipartFile[] files) {
			ResponsDatas<?> response=goodsService.upLoadImg(files);
			return response;
			
		
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
//		/goods/showImg\4.jpg\2018726223333
		String fileName=path.substring(path.lastIndexOf("\\")+1);
		String name=path.substring(15, path.lastIndexOf("\\")+1);
		String Url=filePath+fileName+"/"+name;
		boolean flag=FileTypeUtils.deleteFile(Url);
		if(!flag) {
			FileTypeUtils.deleteFile(Url);
		}
	}
}
