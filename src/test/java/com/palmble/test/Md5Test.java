package com.palmble.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.palmble.service.BaseMenuService;


public class Md5Test {
	@Autowired
	private BaseMenuService baseMenuService;
	@Test
	public void test1() {
		String a="xdsfasfagasdggas gdsg sa sdgsdg";
		String b=new String(a);
		System.out.println(b);
		a=a.replace("a", "b");
		System.out.println(b);
		System.out.println(a==b);
		System.out.println(a.equals(b));
		
	}
	
}
