package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.CcWorkOrderTrace;
import com.sifu.sfcc.model.CcWorkOrderTraceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CcWorkOrderTraceMapper {
    long countByExample(CcWorkOrderTraceExample example);

    int deleteByExample(CcWorkOrderTraceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CcWorkOrderTrace record);

    int insertSelective(CcWorkOrderTrace record);

    List<CcWorkOrderTrace> selectByExample(CcWorkOrderTraceExample example);

    CcWorkOrderTrace selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CcWorkOrderTrace record, @Param("example") CcWorkOrderTraceExample example);

    int updateByExample(@Param("record") CcWorkOrderTrace record, @Param("example") CcWorkOrderTraceExample example);

    int updateByPrimaryKeySelective(CcWorkOrderTrace record);

    int updateByPrimaryKey(CcWorkOrderTrace record);
}