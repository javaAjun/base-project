package com.palmble.test;

public class Md5Test {
	public static void main(String[] args) {
		String value="wx6c387b96a46e1f34;pages/index/index;https://www.palmble.com";
		String appId=value.substring(0,value.indexOf(";"));
		String pagePath=value.substring(value.indexOf(";")+1,value.lastIndexOf(";"));
		String url=value.substring(value.lastIndexOf(";")+1);
		System.out.println(appId);
		System.out.println(pagePath);
		System.out.println(url);
	}

}
