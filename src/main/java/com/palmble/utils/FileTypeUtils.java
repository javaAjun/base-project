package com.palmble.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;


/**
* <p>Title: 文件上传工具类</p>  
* <p>Description:pamable </p>  
* @author WangYanke  
* @date 2018年7月24日 
* @version 1.0
 */
public class FileTypeUtils {
	 public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();     
	{       
        FILE_TYPE_MAP.put("jpg", "FFD8FF"); //JPEG        
        FILE_TYPE_MAP.put("png", "89504E47"); //PNG        
        FILE_TYPE_MAP.put("gif", "47494638"); //GIF       
        FILE_TYPE_MAP.put("tif", "49492A00"); //TIFF      
        FILE_TYPE_MAP.put("bmp", "424D"); //Windows Bitmap       
        FILE_TYPE_MAP.put("dwg", "41433130"); //CAD     
        FILE_TYPE_MAP.put("html", "68746D6C3E"); //HTML      
        FILE_TYPE_MAP.put("rtf", "7B5C727466"); //Rich Text Format      
        FILE_TYPE_MAP.put("xml", "3C3F786D6C");       
        FILE_TYPE_MAP.put("zip", "504B0304");       
        FILE_TYPE_MAP.put("rar", "52617221");       
        FILE_TYPE_MAP.put("psd", "38425053"); //PhotoShop    
        FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A"); //Email [thorough only]     
        FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F"); //Outlook Express     
        FILE_TYPE_MAP.put("pst", "2142444E"); //Outlook        
        FILE_TYPE_MAP.put("office", "D0CF11E0"); //office类型，包括doc、xls和ppt       
        FILE_TYPE_MAP.put("mdb", "000100005374616E64617264204A"); //MS Access       
        FILE_TYPE_MAP.put("wpd", "FF575043"); //WordPerfect     
        FILE_TYPE_MAP.put("eps", "252150532D41646F6265");       
        FILE_TYPE_MAP.put("ps", "252150532D41646F6265");       
        FILE_TYPE_MAP.put("pdf", "255044462D312E"); //Adobe Acrobat     
        FILE_TYPE_MAP.put("qdf", "AC9EBD8F"); //Quicken    
        FILE_TYPE_MAP.put("pwl", "E3828596"); //Windows Password   
        FILE_TYPE_MAP.put("wav", "57415645"); //Wave     
        FILE_TYPE_MAP.put("avi", "41564920");       
        FILE_TYPE_MAP.put("ram", "2E7261FD"); //Real Audio       
        FILE_TYPE_MAP.put("rm", "2E524D46"); //Real Media       
        FILE_TYPE_MAP.put("mpg", "000001BA"); //       
        FILE_TYPE_MAP.put("mov", "6D6F6F76"); //Quicktime       
        FILE_TYPE_MAP.put("asf", "3026B2758E66CF11"); //Windows Media      
        FILE_TYPE_MAP.put("mid", "4D546864"); //MIDI (mid)       
    }
	
	
	/**
	 * <p>Title: 验证文件是否为图片</p>   
	 * @author WangYanke  
	 * @date 2018年7月24日
	 */
	public static boolean isImage(String filePath) {
		String suf = FILE_TYPE_MAP.get(getFileHeader(filePath));
		if (suf == null) {
			return false;
		}
		if (suf.equals("jpg") || suf.equals("jpeg") || suf.equals("gif")
				|| suf.equals("png") || suf.equals("bmp")) {
			return true;
		}
		return false;
	}
	
	/**
	 * <p>Title: 获取文件类型</p>   
	 * @author WangYanke  
	 * @date 2018年7月24日
	 */
	public static String getFileType(String filePath) {
		return FILE_TYPE_MAP.get(getFileHeader(filePath));
	}
	
	/**
	 * <p>Title: 获取文件头部信息</p>   
	 * @author WangYanke  
	 * @date 2018年7月24日
	 */
	public static String getFileHeader(String filePath) {
		FileInputStream is = null;
		String value = null;
		try {
			is = new FileInputStream(filePath);
			byte[] b = new byte[3];
			is.read(b, 0, b.length);
			value = bytesToHexString(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		return value;
	}
	
	/**
	 * <p>Title: byte转16进制</p>   
	 * @author WangYanke  
	 * @date 2018年7月24日
	 * @param src 数据源
	 */
	private static String bytesToHexString(byte[] src) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;
		for (int i = 0; i < src.length; i++) {
			hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
			if (hv.length() < 2) {
				builder.append(0);
			}
			builder.append(hv);
		}
		return builder.toString();
	}
	
	/**
	 * <p>Title: 图片需增加水印调用此方法</p>   
	 * @author WangYanke  
	 * @date 2018年7月25日
	 * @param in 图片输入流  targetPath 图片保存位置 front 水印文字
	 */
	public static boolean markImageByIcon(InputStream in, String targetPath,String front){
		Image srcImg = null;
		try{
			if (in != null) {
				srcImg = ImageIO.read(in);
			} else {
				return false;
			}
			return makeImageByIcon(srcImg,targetPath,front);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(in!=null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * <p>Title: makeImageByIcon</p>   
	 * @author WangYanke  
	 * @date 2018年7月25日
	 * @param srcImg 图片源  targetPath 图片保存地址  front:水印文字
	 */
	private static boolean makeImageByIcon(Image srcImg, String targetPath,String front) throws Exception{
		if (srcImg == null)
			return false;
		int width = srcImg.getWidth(null);
		int height = srcImg.getHeight(null);
		if (width <= 0 || height <= 0) {
			return false;
		}
		OutputStream os = null;
		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		// 设置对线段的锯齿状边缘处理
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(
				srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,
				0, null);
		// 旋转
		// if ( degree != null ) {
		// g.rotate(Math.toRadians(degree), buffImg.getWidth()/2,
		// buffImg.getHeight()/2);
		// }
		// g.drawImage(srcImg,width,width, null);
		// 水印
		int fontSize = 13;
		g.setFont(new Font("宋体", Font.CENTER_BASELINE, fontSize));// 宋体，微雅，13
		g.setColor(Color.white);
		g.setComposite(AlphaComposite
				.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		String pressText = front;
		int x = 0;
		int y = fontSize;
		g.drawString(pressText, x, y);
		g.dispose();
		try {
			// 生成图片
			os = new FileOutputStream(targetPath);
			String suffix = targetPath.substring(
					targetPath.lastIndexOf(".") + 1).toLowerCase();
			ImageIO.write(buffImg, suffix, os);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage() != null ? e.getMessage()
					: "null生成图片失败");
		} finally {
			if (os != null)
				os.close();
		}
	}
	
	/**
	 * <p>Title: io流上传文件到制定目录</p>   
	 * @author WangYanke  
	 * @date 2018年7月25日
	 * @param is 输入流 path:生成文件保存位置
	 */
	public static void makeFile(InputStream is, String path) {
		FileOutputStream out=null;
		try {
			out = new FileOutputStream(path);
			//创建一个缓冲区
			byte buffer[] = new byte[1024];
			//判断输入流中的数据是否已经读完的标识       
			int len = 0;
			try {
				while((len=is.read(buffer))>0){
				    //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
				    out.write(buffer, 0, len);
				  }
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				is.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
