package com.sifu.sfcc.service;

import com.sifu.sfcc.dto.ProjectParam;
import com.sifu.sfcc.model.XmkProject;

import java.util.List;

public interface XmkProjectService {

    boolean exportProject();

    boolean zlExportProject();

    List<XmkProject> getProjectByName(Integer pageSize, Integer pageNum, String projectName);

    String importXmkProject();
    
    ProjectParam getCountByLocation(String location);
    
    ProjectParam getRecommendType(String location,Integer pageNum);
    
    ProjectParam getRecommend(String location,Integer pageNum);
    
    ProjectParam getTotal(String location);
    
    ProjectParam getLocation(String name,Integer pageNum);
}
