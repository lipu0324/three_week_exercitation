package com.sifu.sfcc.service;

import java.util.Map;

/**
 * 外呼任务管理Service
 * Created by sifu on 2022/02/10.
 */
public interface CcScoreService {
    /**
     * 问卷管理
     */
    long scoreCount();
    
    Map<String,Long> count(String startTime,String endTime,String code);
   
}
