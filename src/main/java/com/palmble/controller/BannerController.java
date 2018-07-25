package com.palmble.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.PageInfo;
import com.palmble.base.PalmbleBaseController;
import com.palmble.entity.Banner;
import com.palmble.service.BannerService;
import com.palmble.utils.FileTypeUtils;

@RestController
@RequestMapping("/banner")
public class BannerController extends PalmbleBaseController{
	
	@Value("${image.location}")
	private String filePath;
	@Autowired
	BannerService bannerService;
	@RequestMapping("/list")
	public PageInfo<Banner> getList(@RequestParam Map<String, Object> mvm) {
		return bannerService.getBannerList(mvm);
	}
	
	/**
	 * <p>Title: banner图上传</p>   
	 * @author WangYanke  
	 * @date 2018年7月24日
	 */
	@RequestMapping("/bannerImgUpload")
	public void bannerImgUpload(@RequestParam(value="img", required=false)MultipartFile file) {
		if(file!=null&&file.getSize()!=0) {
			String fileContentType = file.getContentType();
			String fileFileName = file.getOriginalFilename();
			System.out.println(fileContentType);
			System.out.println("上传的文件原名称:"+fileFileName);
			Date date = new Date();  
			String dataForm = new SimpleDateFormat("yyyy-MM-dd").format(date); 
			String path=filePath+"/"+dataForm;
			File f = new File(path);  //根据日期创建文件夹
			if(!f.exists()){  
			    f.mkdirs();   
			} 
			InputStream is = null;
			 //创建一个文件输出流
			try {
				is = file.getInputStream();
			} catch (IOException e) {
				System.out.println("IO流异常");
				e.printStackTrace();
			}
			path=path + "\\" + fileFileName;
			FileTypeUtils.makeFile(is,path);
		}
	}
	
}
