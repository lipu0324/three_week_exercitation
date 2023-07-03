package com.sifu.sfcc.service;

import com.sifu.sfcc.model.UmsMember;
import java.util.List;

/**
 * 会员等级管理Service
 * Created by sifu on 2018/4/26.
 */
public interface UmsMemberService {
    /**
     * 获取所有会员登录
     */
    List<UmsMember> list(String name , String phone, String startTime,String endTime,long label,String startCount,String endCount,String salesman, Integer status,Integer pageSize , Integer pageNum);

    int add(UmsMember member);

    int update(UmsMember member);

    int delete(Long id);

    UmsMember get(Long id);
    
    int ban(Long id , Integer status);
    
    int batchBan(List<Long> ids);
    
    int addBatch(List<UmsMember> member);
    
    int updateBatch(List<UmsMember> member);
    
    long total();
}
