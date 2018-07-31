package com.palmble.test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.palmble.entity.Account;
import com.palmble.entity.Bill;
import com.palmble.entity.OrderInfo;
import com.palmble.service.AccountService;
import com.palmble.service.BillService;
import com.palmble.service.OrderInfoService;
import com.palmble.utils.TransactionUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BaseTest {
	@Autowired
	private BillService billService;
	@Autowired
	private AccountService accountService;

	@Test
	public void getSqlsession() throws IllegalArgumentException, IllegalAccessException {
		Account account=accountService.getById(1);
		System.out.println(account.getBalance());
	}

}
