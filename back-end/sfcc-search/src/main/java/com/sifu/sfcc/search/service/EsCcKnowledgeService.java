package com.sifu.sfcc.search.service;

import com.sifu.sfcc.search.domain.CcKnowledge;
import org.springframework.data.domain.Page;

public interface EsCcKnowledgeService {
    int importAll();


    Page<CcKnowledge> search(String keyword, Integer pageNum, Integer pageSize);

    Page<CcKnowledge> search(String keyword, Integer pageNum, Integer pageSize, Integer sort);
}
