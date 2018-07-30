package com.palmble.entity;

public class Result<T> {
	private String msg;
	private Integer code;
	private String url;
	private T data;
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Result(String msg, Integer code, String url) {
		super();
		this.msg = msg;
		this.code = code;
		this.url = url;
	}
	public Result() {
	}
	
	
}
