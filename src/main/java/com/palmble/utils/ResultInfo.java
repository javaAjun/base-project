package com.palmble.utils;
/**
 * 
* <p>Title: ResultInfo</p>  
* <p>Description:pamable </p>  
* @author WangYanke  
* @date 2018年6月19日 
* @version 1.0
 */
public class ResultInfo {
	/** 结果代码（默认值为-1，未知错误） */
	private int code = -1;
	/** 信息 */
	private Object msg;
	
	public ResultInfo(int code, Object msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	}
	
}
