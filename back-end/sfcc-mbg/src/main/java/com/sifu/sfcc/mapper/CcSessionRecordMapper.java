package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.CcSessionRecord;
import com.sifu.sfcc.model.CcSessionRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CcSessionRecordMapper {
    long countByExample(CcSessionRecordExample example);

    int deleteByExample(CcSessionRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CcSessionRecord record);

    int insertSelective(CcSessionRecord record);

    List<CcSessionRecord> selectByExample(CcSessionRecordExample example);

    CcSessionRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CcSessionRecord record, @Param("example") CcSessionRecordExample example);

    int updateByExample(@Param("record") CcSessionRecord record, @Param("example") CcSessionRecordExample example);

    int updateByPrimaryKeySelective(CcSessionRecord record);

    int updateByPrimaryKey(CcSessionRecord record);
}