package com.palmble.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.palmble.dal.MemberUserDao;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BaseTest {
	@Autowired
	MemberUserDao dao;
	@Test
	public void getSqlsession(){
		System.out.println(dao.find(null).size());
		
	}
}
