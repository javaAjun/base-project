package com.palmble.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class VifityCodeController {

	private Logger logger = LoggerFactory.getLogger(VifityCodeController.class);
	
	 /**
	  * * 生成验证码
	 * @throws Exception 
	 */
	@RequestMapping(value="/checkRedis")  
	public String checkRedis(String key) throws Exception{  
      
    String value= r.get(ssdf
      
    return value;  
} 

	
	@RequestMapping(value="/api/SecAdminUser/setSessionId")  
	public String setSessionId(HttpServletRequest request,String key,String value){  
      
    request.getSession().setAttribute(key,value);  
      
    return "success";  
} 



	@RequestMapping(value="/api/SecAdminUser/getSessionId")  
    public String getSessionId(HttpServletRequest request,String key){  
          
        Object o = request.getSession().getAttribute(key);  
        return "端口=" + request.getLocalPort() +  " sessionId=" + request.getSession().getId() +"<br/>"+o;  
    }  
	@RequestMapping(value = "/vifityCodeController/getVerify")
	public void getVerify(HttpServletRequest request, HttpServletResponse response) {
	 try {
	  response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
	  response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
	  response.setHeader("Cache-Control", "no-cache");
	  response.setDateHeader("Expire", 0);
	  RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
	  randomValidateCode.getRandcode(response,r);//输出验证码图片方法
	 } catch (Exception e) {
	  logger.error("获取验证码失败>>>> ", e);
	 }
	}
	
	@RequestMapping(value = "/vifityCodeController/checkVerify", method = RequestMethod.POST, headers = "Accept=application/json")
	public boolean checkVerify(@RequestBody Map<String, Object> requestMap, HttpSession session) {
	 try{
	  //从session中获取随机数
	  String inputStr = requestMap.get("inputStr").toString();
	  String random = r.get(inputStr);
	  if (random == null) {
	   return false;
	  }
	  if (random.equals(inputStr)) {
	   return true;
	  } else {
	   return false;
	  }
	 }catch (Exception e){
	  logger.error("验证码校验失败", e);
	  return false;
	 }
	}
}
