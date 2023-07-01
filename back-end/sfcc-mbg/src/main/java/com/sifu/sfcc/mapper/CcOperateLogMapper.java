package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.CcOperateLog;
import com.sifu.sfcc.model.CcOperateLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CcOperateLogMapper {
    long countByExample(CcOperateLogExample example);

    int deleteByExample(CcOperateLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CcOperateLog record);

    int insertSelective(CcOperateLog record);

    List<CcOperateLog> selectByExampleWithBLOBs(CcOperateLogExample example);

    List<CcOperateLog> selectByExample(CcOperateLogExample example);

    CcOperateLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CcOperateLog record, @Param("example") CcOperateLogExample example);

    int updateByExampleWithBLOBs(@Param("record") CcOperateLog record, @Param("example") CcOperateLogExample example);

    int updateByExample(@Param("record") CcOperateLog record, @Param("example") CcOperateLogExample example);

    int updateByPrimaryKeySelective(CcOperateLog record);

    int updateByPrimaryKeyWithBLOBs(CcOperateLog record);

    int updateByPrimaryKey(CcOperateLog record);
}