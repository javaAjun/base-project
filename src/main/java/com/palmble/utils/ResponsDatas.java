package com.palmble.utils;

import java.io.Serializable;
import java.util.Map;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author malingbing
 * Restful统一Json响应对象封装
 * @param <T>
 */
public class ResponsDatas<T> implements Serializable {

	  private static final long serialVersionUID = 1L;

	    private final static String SUCCESS_CODE = "200";

	    /**
	     * 返回状态码
	     */
	    private String status;

	    /**
	     * 返回消息
	     */
	    private String message;

	    /**
	     * 返回内容
	     */
	    private T data;

	    /**
	     * 分页信息
	     */
	    private PageInfoUtil pageInfo;

	    /**
	     * 其他内容
	     */
	    private Map<String, Object> ext;

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public T getData() {
	        return data;
	    }

	    public void setData(T data) {
	        this.data = data;
	    }

	    public Map<String, Object> getExt() {
	        return ext;
	    }

	    public void setExt(Map<String, Object> ext) {
	        this.ext = ext;
	    }

	   
	   

	    public PageInfoUtil getPageInfo() {
			return pageInfo;
		}

		public void setPageInfo(PageInfoUtil pageInfo) {
			this.pageInfo = pageInfo;
		}

		public ResponsDatas(){
	        this.status = SUCCESS_CODE;
	        this.message = "SUCCESS";
	    }

	    public ResponsDatas(String status, String message) {
	        this.status = status;
	        this.message = message;
	    }

	    public ResponsDatas(String status, String message, T data) {
	        this.status = status;
	        this.message = message;
	        this.data = data;
	    }

	    public ResponsDatas(String status, String message, T data, Map<String, Object> ext) {
	        this.status = status;
	        this.message = message;
	        this.data = data;
	        this.ext = ext;
	    }

	    public ResponsDatas(String status, String message, T data, PageInfoUtil PageInfoUtil) {
	        this.status = status;
	        this.message = message;
	        this.data = data;
	        this.pageInfo = PageInfoUtil;
	    }

	    public ResponsDatas(String status, String message, T data, Map<String, Object> ext, PageInfoUtil PageInfoUtil) {
	        this.status = status;
	        this.message = message;
	        this.data = data;
	        this.ext = ext;
	        this.pageInfo = PageInfoUtil;
	    }

	    public ResponsDatas(String status, String message, T data, Long total, Integer pageNo, Integer pageSize){
	        PageInfoUtil PageInfoUtil = new PageInfoUtil(total, pageNo, pageSize);
	        this.status = status;
	        this.message = message;
	        this.data = data;
	        this.pageInfo = PageInfoUtil;
	    }

	    public ResponsDatas(String status, String message, T data, Map<String, Object> ext, Long total, Integer pageNo, Integer pageSize){
	    	 PageInfoUtil PageInfoUtil = new PageInfoUtil(total, pageNo, pageSize);
	        PageInfoUtil.setPageSize(pageSize);
	        this.status = status;
	        this.message = message;
	        this.data = data;
	        this.ext = ext;
	        this.pageInfo = PageInfoUtil;
	    }

	    //快速返回成功
	    public static <T>ResponsDatas success(){
	        return new ResponsDatas<T>(SUCCESS_CODE,"请求成功",null);
	    }

	    public static <T>ResponsDatas success(T result){
	        return new ResponsDatas<T>(SUCCESS_CODE,"请求成功",result);
	    }

	    public static <T>ResponsDatas success(String message, T result){
	        return new ResponsDatas<T>(SUCCESS_CODE,message,result);
	    }

	    public static <T>ResponsDatas success(String message, T result, Map<String, Object> extra){
	        return new ResponsDatas<T>(SUCCESS_CODE,message,result, extra);
	    }

	    public static <T>ResponsDatas success(T result, Long total, Integer pageNo, Integer pageSize){
	    	 PageInfoUtil PageInfoUtil = new PageInfoUtil(total, pageNo, pageSize);
	        PageInfoUtil.setPageSize(pageSize);
	        return new ResponsDatas<T>(SUCCESS_CODE,"请求成功",result, PageInfoUtil);
	    }

	    public static <T>ResponsDatas success(T result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
	        PageInfoUtil PageInfoUtil = new PageInfoUtil(total, pageNo, pageSize);
	        return new ResponsDatas<T>(SUCCESS_CODE,"请求成功",result, extra,PageInfoUtil);
	    }

	    public static <T>ResponsDatas success(String message, T result, Long total, Integer pageNo, Integer pageSize){
	        PageInfoUtil PageInfoUtil = new PageInfoUtil(total, pageNo, pageSize);
	        return new ResponsDatas<T>(SUCCESS_CODE,message,result,PageInfoUtil);
	    }

	    public static <T>ResponsDatas success(String message, T result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
	        PageInfoUtil PageInfoUtil = new PageInfoUtil(total, pageNo, pageSize);
	        return new ResponsDatas<T>(SUCCESS_CODE,message,result, extra,PageInfoUtil);
	    }

	    //快速返回失败状态
	    public static <T>ResponsDatas fail(){
	        return new ResponsDatas<T>(ErrorCode.SYSTEM_ERROR.getCode(),ErrorCode.SYSTEM_ERROR.getMessage());
	    }

	    public static <T>ResponsDatas fail(T result){
	        return new ResponsDatas<T>(ErrorCode.SYSTEM_ERROR.getCode(),ErrorCode.SYSTEM_ERROR.getMessage(),result);
	    }

	    public static <T>ResponsDatas fail(String message, T result){
	        return new ResponsDatas<T>(ErrorCode.SYSTEM_ERROR.getCode(),message,result);
	    }

	    public <T>ResponsDatas fail(String message, T result, Map<String, Object> extra){
	        return new ResponsDatas<T>(ErrorCode.SYSTEM_ERROR.getCode(),message,result, extra);
	    }

	    public static <T>ResponsDatas fail(ErrorCode errorCode){
	        return new ResponsDatas<T>(errorCode.getCode(),errorCode.getMessage());
	    }

	    public static <T>ResponsDatas fail(ErrorCode errorCode, T result){
	        return new ResponsDatas<T>(errorCode.getCode(),errorCode.getMessage(),result);
	    }

	    public static <T>ResponsDatas fail(ErrorCode errorCode, String message, T result){
	        return new ResponsDatas<T>(errorCode.getCode(),message,result);
	    }

	    public static <T>ResponsDatas fail(ErrorCode errorCode, String message, T result, Map<String, Object> extra){
	        return new ResponsDatas<T>(errorCode.getCode(),message,result, extra);
	    }

	    //快速返回自定义状态码
	    public static <T>ResponsDatas result(String statusCode, String message){
	        return new ResponsDatas<T>(statusCode,message);
	    }

	    public static <T>ResponsDatas result(String statusCode, String message, T result){
	        return new ResponsDatas<T>(statusCode,message,result);
	    }

	    public static <T>ResponsDatas result(String statusCode, String message, T result, Map<String, Object> extra){
	        return new ResponsDatas<T>(statusCode,message,result, extra);
	    }

	    public static <T>ResponsDatas result(String statusCode, String message, T result, Long total, Integer pageNo, Integer pageSize){
	        PageInfoUtil PageInfoUtil = new PageInfoUtil(total, pageNo, pageSize);
	        return new ResponsDatas<T>(statusCode,message,result, PageInfoUtil);
	    }

	    public static <T>ResponsDatas result(String statusCode, String message, T result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
	        PageInfoUtil PageInfoUtil = new PageInfoUtil(total, pageNo, pageSize);
	        return new ResponsDatas<T>(statusCode,message,result, extra,PageInfoUtil);
	    }

	    //快速返回Http状态
	    public static <T>ResponsDatas httpStatus(HttpStatus httpStatus, String message){
	        return result(httpStatus.toString(),message);
	    }

	    public static <T>ResponsDatas httpStatus(HttpStatus httpStatus, String message, T result){
	        return result(httpStatus.toString(),message,result);
	    }

	    public static <T>ResponsDatas httpStatus(HttpStatus httpStatus, String message, T result, Map<String, Object> extra){
	        return result(httpStatus.toString(),message,result, extra);
	    }

	    public static <T>ResponsDatas httpStatus(HttpStatus httpStatus, String message, T result, Long total, Integer pageNo, Integer pageSize){
	        PageInfoUtil PageInfoUtil = new PageInfoUtil(total, pageNo, pageSize);
	        return result(httpStatus.toString(),message,result, total, pageNo, pageSize);
	    }

	    public static <T>ResponsDatas httpStatus(HttpStatus httpStatus, String message, T result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
	        PageInfoUtil PageInfoUtil = new PageInfoUtil(total, pageNo, pageSize);
	        return result(httpStatus.toString(),message,result, extra, total, pageNo, pageSize);
	    }
	}
