package com.sifu.sfcc.service;

import com.sifu.sfcc.dto.CcArchivesParam;
import com.sifu.sfcc.model.CcArchives;
import java.util.List;
import java.util.Map;

/**
 * 知识库管理Service
 * Created by sifu on 2021/10/21.
 */
public interface CcArchivesService {
    /**
     * 获取所有标签
     */
    List<CcArchivesParam> list(String name ,String code,String urgency,String nameOrUrgency, Integer pageSize , Integer pageNum);
    
    String export(String name ,String code,String urgency,String nameOrUrgency);
    
    CcArchivesParam getArchivesByPhone(String phone);
    
    List<CcArchives> list();
    
    int add(CcArchives member);

    int update(CcArchives member);

    int delete(Long id);

    CcArchivesParam get(Long id);
    
    CcArchivesParam getArchives(String code);
    
    long total();
    
    Map<String,Long> getArchivesCount(String label ,String startTime,String endTime);
    
    long labelCount(String lable);
}
