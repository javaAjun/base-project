package com.palmble.dal;

import java.util.List;
import java.util.Map;

import com.palmble.entity.MemberUser;


public interface MemberUserDao{
    
    int insert(MemberUser memberUser);
    
    int insertFully(MemberUser memberUser);
    
    int deleteById(Integer id);
    
    int updateById(MemberUser memberUser);

    int updateFullyById(MemberUser memberUser);
    
    MemberUser getById(Integer id);
    
    //int startRow, int pageSize, object2map
    
    List<MemberUser> find(Map<String, Object> params);

    int count(Map<String, Object> params);
    
    List<MemberUser> fuzzyQuery(Map<String, Object> params);

}