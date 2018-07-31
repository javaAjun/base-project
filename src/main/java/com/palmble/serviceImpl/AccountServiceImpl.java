package com.palmble.serviceImpl;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palmble.dal.AccountDao;
import com.palmble.entity.Account;
import com.palmble.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDao accountDao;
	@Override
	public int insert(Account account) {
		return accountDao.insert(account);
	}

	@Override
	public int insertFully(Account account) {
		return accountDao.insertFully(account);
	}

	@Transactional
	@Override
	public int deleteById(Integer id) {
		return accountDao.deleteById(id);
	}

	@Transactional
	@Override
	public int updateById(Account account) {
		return accountDao.updateById(account);
	}
	@Transactional
	@Override
	public int updateByUserId(Account account) {
		return accountDao.updateByUserId(account);
	}
	@Transactional
	@Override
	public int updateFullyById(Account account) {
		return accountDao.updateFullyById(account);
	}

	@Override
	public Account getById(Integer id) {
		return accountDao.getById(id);
	}

	@Override
	public Account getByUserId(Integer id) {
		return accountDao.getByUserId(id);
	}

	@Override
	public int count(Map<String, Object> params) {
		return accountDao.count(params);
	}

}
