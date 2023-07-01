package com.sifu.sfcc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sifu.sfcc.dto.CcArchivesExport;
import com.sifu.sfcc.dto.CcArchivesParam;

public interface CcArchivesDao {

	 List<CcArchivesParam> getArchives(@Param("name")String name ,@Param("code")String code,@Param("urgency")String urgency,@Param("nameOrUrgency")String nameOrUrgency);
	 List<CcArchivesExport> getArchivesExport(@Param("name")String name ,@Param("code")String code,@Param("urgency")String urgency,@Param("nameOrUrgency")String nameOrUrgency);
	 List<CcArchivesParam> getArchivesCount(@Param("label")String label ,@Param("startTime")String startTime,@Param("endTime")String endTime);
	 long labelCount(@Param("label")String lable);
}
