package com.palmble.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.palmble.service.BaseMenuService;


public class Md5Test {
	@Autowired
	private BaseMenuService baseMenuService;
	@Test
	public void test1() {
		System.out.println(baseMenuService);
	}
}
