package com.sifu.sfcc.search.service;

import org.springframework.data.domain.Page;
import com.sifu.sfcc.search.domain.XmkPolicy;

public interface EsXmkPolicyService {
    int importAll();

    Page<XmkPolicy> search(String keyword, Integer i, Integer pageSize);

    Page<XmkPolicy> search(String keyword, Integer i, Integer pageSize, Integer sort);
}
