package com.sifu.sfcc.dao;

import com.sifu.sfcc.dto.PolicyParam;
import com.sifu.sfcc.model.XmkPolicy;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface XmkPolicyDao {
    Long zlExportPolicy(List<XmkPolicy> xmkPolicyList);
    
    List<XmkPolicy> getPolicyCount(@Param("city") String city,@Param("title")String title);
    
    PolicyParam getPolicyRecommend(@Param("city") String city,@Param("pageNum")Integer pageNum);
}
