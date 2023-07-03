package com.sifu.sfcc.service;

import com.sifu.sfcc.model.CcLabel;

import java.util.List;

/**
 * 标签管理Service
 * Created by sifu on 2021/10/21.
 */
public interface CcLabelService {
    /**
     * 获取所有标签
     */
    List<CcLabel> list(String name , Integer status,Integer pageSize , Integer pageNum);

    int add(CcLabel member);

    int update(CcLabel member);

    int delete(Long id);

    CcLabel get(Long id);
    
    int updateStatus(Long id , Integer status);
    
    int batchDelete(List<Long> ids);
}
