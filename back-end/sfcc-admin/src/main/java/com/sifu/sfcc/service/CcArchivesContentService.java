package com.sifu.sfcc.service;

import com.sifu.sfcc.model.CcArchivesContent;
import java.util.List;

/**
 * 知识库管理Service
 * Created by sifu on 2021/10/21.
 */
public interface CcArchivesContentService {
    /**
     * 获取所有标签
     */
    List<CcArchivesContent> list(long archivesId, Integer pageSize , Integer pageNum);
    
    List<CcArchivesContent> list(long archivesId);
    
    int add(CcArchivesContent ac);

    int update(CcArchivesContent ac);

    int delete(Long id);
    
    int updateBatch(List<CcArchivesContent> content);
}
