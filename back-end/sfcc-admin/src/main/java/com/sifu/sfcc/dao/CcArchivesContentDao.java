package com.sifu.sfcc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sifu.sfcc.model.CcArchivesContent;

public interface CcArchivesContentDao {

//	List<CcLabelParam> getLabel(@Param("contentId") long contentId);
	int updateBatch(@Param("list") List<CcArchivesContent> list);
	  
}
