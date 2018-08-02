package com.palmble.dal;

import java.util.List;
import java.util.Map;
import com.palmble.entity.AdminGroups;


public interface AdminGroupsDao{
    
    int insert(AdminGroups adminGroups);
    
    int insertFully(AdminGroups adminGroups);
    
    int deleteById(Integer id);
    
    int updateById(AdminGroups adminGroups);

    int updateFullyById(AdminGroups adminGroups);
    
    AdminGroups getById(Integer id);
    
    //int startRow, int pageSize, object2map
    
    List<AdminGroups> find(Map<String, Object> params);

    int count(Map<String, Object> params);

}