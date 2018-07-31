package com.palmble.test;

import org.junit.Test;

import com.palmble.utils.TransactionUtil;

public class Md5Test {
	@Test
	public  void test(){
		String str=TransactionUtil.getTransactionNum(4);
		System.out.println(str);
	}

}
