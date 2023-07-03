package com.sifu.sfcc.service;

import com.sifu.sfcc.dto.CcScoreParam;
import com.sifu.sfcc.model.CcSessionRecord;

import java.util.List;

/**
 * 知识库管理Service
 * Created by sifu on 2021/10/21.
 */
public interface CcSessionService {
    /**
     * 获取所有标签
     */
    List<CcSessionRecord> list(String phone , Integer pageSize , Integer pageNum);
    
    int createWorkOrder(String phone,String sessionId);
    
    int add(CcSessionRecord member);
    
    int createScore(CcScoreParam param);

}
