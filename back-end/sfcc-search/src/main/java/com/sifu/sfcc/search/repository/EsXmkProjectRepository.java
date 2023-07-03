package com.sifu.sfcc.search.repository;

import com.sifu.sfcc.search.domain.XmkProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 项目库ES操作类
 * Created by cc on 2018/6/19.
 */
public interface EsXmkProjectRepository extends ElasticsearchRepository<XmkProject, Long> {


    Page<XmkProject> findBySXmNameOrSXmInfo(String sXmName, String sXmInfo, Pageable pageable);
}
