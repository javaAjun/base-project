package com.palmble.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.palmble.entity.Result;
import com.palmble.service.BannerService;
import com.palmble.utils.FileTypeUtils;
import com.palmble.utils.ResultInfo;

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
	 * @return 
	 * @date 2018年7月24日
	 */
	@RequestMapping("/bannerImgUpload")
	public Result bannerImgUpload(@RequestParam(value="img", required=false)MultipartFile file) {
		Result result=new Result();
		if(file!=null&&file.getSize()!=0) {
			String fileContentType = file.getContentType();
			String fileFileName = file.getOriginalFilename();
			System.out.println(fileContentType);
			System.out.println("上传的文件原名称:"+fileFileName);
			Calendar cale = null;  
	        cale = Calendar.getInstance();  
	        int year = cale.get(Calendar.YEAR);  
	        int month = cale.get(Calendar.MONTH) + 1;  
	        int day = cale.get(Calendar.DATE); 
	        String path=filePath+"/"+year+""+month+""+day;
			File f = new File(path);  //根据当前日期创建文件夹
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
			Boolean flag = FileTypeUtils.makeFile(is,path);
			if(flag) {
				result.setCode(0);
				result.setUrl(path);
				result.setMsg("图片上传成功");
			}else {
				result.setCode(1);
				result.setUrl(path);
				result.setMsg("图片上传异常");
			}
		}
		return result;
	}
	
}
