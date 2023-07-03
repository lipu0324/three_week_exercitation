package com.sifu.sfcc.dao;

import com.sifu.sfcc.dto.ProjectParam;
import com.sifu.sfcc.model.XmkProject;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface XmkProjectDao {
	
    Long zlExportProject(List<XmkProject> xmkProjectList);

    List<XmkProject> queryXmkProject();
    
    List<XmkProject> getCountByLocation(@Param("city") String city,@Param("area") String area);
    
    ProjectParam getRecommendType(@Param("city") String city,@Param("area") String area,@Param("pageNum")Integer pageNum);
    
    ProjectParam getRecommend(@Param("city") String city,@Param("area") String area,@Param("type") String type,@Param("pageNum")Integer pageNum);
    
    ProjectParam getTotal(@Param("city") String city,@Param("area") String area,@Param("type")String type);

    ProjectParam getLocation(@Param("name") String name, @Param("pageNum")Integer pageNum);   
    
}
