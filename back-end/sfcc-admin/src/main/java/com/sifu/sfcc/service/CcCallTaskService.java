package com.sifu.sfcc.service;

import com.sifu.sfcc.dto.CcCallTaskParam;
import com.sifu.sfcc.model.CcCallTask;
import java.util.List;

/**
 * 外呼任务管理Service
 * Created by sifu on 2022/02/10.
 */
public interface CcCallTaskService {
    /**
     * 问卷管理
     */
    List<CcCallTaskParam> list(String title , String status,String startTime, String endTime,Integer pageSize , Integer pageNum);
    
    int add(CcCallTask naire);

    int update(CcCallTask naire);
    
    int updateStatus(CcCallTask naire);

    int delete(Long id);

    CcCallTask get(Long id);
   
}
