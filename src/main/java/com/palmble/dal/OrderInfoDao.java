package com.palmble.dal;

import java.util.List;
import java.util.Map;
import com.palmble.entity.OrderInfo;


public interface OrderInfoDao{
    
//    int insert(OrderInfo orderInfo);
    
//    int insertFully(OrderInfo orderInfo);
    
    int deleteById(Integer id);
    
//    int updateById(OrderInfo orderInfo);

//    int updateFullyById(OrderInfo orderInfo);
    
    OrderInfo getById(Integer id);
    
    //int startRow, int pageSize, object2map
    
    List<OrderInfo> find(Map<String, Object> params);

    int count(Map<String, Object> params);
    
    List<OrderInfo> findSimpleResult(Map<String, Object> params);

}