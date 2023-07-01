package com.sifu.sfcc.service;

import com.sifu.sfcc.model.CcKnowledge;

import java.util.List;

/**
 * 知识库管理Service
 * Created by sifu on 2021/10/21.
 */
public interface CcKnowledgeService {
    /**
     * 获取所有标签
     */
    List<CcKnowledge> list(String question , Integer pageSize , Integer pageNum);
    
    List<CcKnowledge> list(String question);
    
    int add(CcKnowledge member);

    int update(CcKnowledge member);

    int delete(Long id);

    CcKnowledge get(Long id);
    
    long total();
}
