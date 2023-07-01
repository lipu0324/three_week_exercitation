package com.sifu.sfcc.dao;

import com.sifu.sfcc.dto.CcWorkOrderCount;
import com.sifu.sfcc.model.CcWorkOrder;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CcWorkOrderDao {

	 List<CcWorkOrder> listWorkOrder(@Param("loginId")Long loginId , @Param("status") Integer status, @Param("currentDealerId") Long currentDealerId);
	 
	 
	 List<CcWorkOrderCount> getCount(@Param("startTime")String startTime , @Param("endTime") String endTime, @Param("dealerId") Long dealerId);
	 
}
