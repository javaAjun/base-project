package com.palmble.service;

import java.util.Map;

import com.palmble.entity.Account;
public interface AccountService {
	  int insert(Account account);
	    
	    int insertFully(Account account);
	    
	    int deleteById(Integer id);
	    
	    int updateById(Account account);
	    
	    int updateByUserId(Account account);

	    int updateFullyById(Account account);
	    
	    Account getById(Integer id);
	    
	    Account getByUserId(Integer id);
	    
	    

	    int count(Map<String, Object> params);
}
