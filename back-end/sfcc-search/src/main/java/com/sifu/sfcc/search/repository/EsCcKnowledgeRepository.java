package com.sifu.sfcc.search.repository;

import com.sifu.sfcc.search.domain.CcKnowledge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 项目库ES操作类
 * Created by cc on 2018/6/19.
 */
public interface EsCcKnowledgeRepository extends ElasticsearchRepository<CcKnowledge, Long> {


    Page<CcKnowledge> findByAnswer(String answer, Pageable pageable);
}
