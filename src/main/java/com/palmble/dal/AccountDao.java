package com.palmble.dal;

import java.util.Map;

import com.palmble.entity.Account;


public interface AccountDao{
    
    int insert(Account account);
    
    int insertFully(Account account);
    
    int deleteById(Integer id);
    
    int updateById(Account account);
    
    int updateByUserId(Account account);

    int updateFullyById(Account account);
    
    Account getById(Integer id);
    
    Account getByUserId(Integer id);
    
    //int startRow, int pageSize, object2map
    

    int count(Map<String, Object> params);

}