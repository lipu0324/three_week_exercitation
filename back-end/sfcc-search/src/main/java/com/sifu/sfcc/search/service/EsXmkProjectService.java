package com.sifu.sfcc.search.service;

import com.sifu.sfcc.search.domain.XmkProject;
import org.springframework.data.domain.Page;

public interface EsXmkProjectService {
    int importAll();

    Page<XmkProject> search(String keyword, Integer pageNum, Integer pageSize);
    Page<XmkProject> search(String keyword, Integer pageNum, Integer pageSize, Integer sort);
}
