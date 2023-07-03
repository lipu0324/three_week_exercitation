package com.sifu.sfcc.search.service;

import com.sifu.sfcc.search.domain.XmkTels;
import org.springframework.data.domain.Page;

public interface EsXmkTelsService {
    int importAll();

    Page<XmkTels> search(String keyword, Integer pageNum, Integer pageSize, Integer sort);
}
