package com.sifu.sfcc.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.sifu.sfcc.dto.CcCallTaskParam;

public interface CcCallTaskDao {

	List<CcCallTaskParam> list(@Param("title")String title, @Param("status")String status, @Param("startTime")String startTime, @Param("endTime")String endTime);
}
