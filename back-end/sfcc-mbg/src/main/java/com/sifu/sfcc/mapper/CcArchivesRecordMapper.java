package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.CcArchivesRecord;
import com.sifu.sfcc.model.CcArchivesRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CcArchivesRecordMapper {
    long countByExample(CcArchivesRecordExample example);

    int deleteByExample(CcArchivesRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CcArchivesRecord record);

    int insertSelective(CcArchivesRecord record);

    List<CcArchivesRecord> selectByExample(CcArchivesRecordExample example);

    CcArchivesRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CcArchivesRecord record, @Param("example") CcArchivesRecordExample example);

    int updateByExample(@Param("record") CcArchivesRecord record, @Param("example") CcArchivesRecordExample example);

    int updateByPrimaryKeySelective(CcArchivesRecord record);

    int updateByPrimaryKey(CcArchivesRecord record);
}