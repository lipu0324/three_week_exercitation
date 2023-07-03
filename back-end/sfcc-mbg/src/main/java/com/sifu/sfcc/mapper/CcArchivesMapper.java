package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.CcArchives;
import com.sifu.sfcc.model.CcArchivesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CcArchivesMapper {
    long countByExample(CcArchivesExample example);

    int deleteByExample(CcArchivesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CcArchives record);

    int insertSelective(CcArchives record);

    List<CcArchives> selectByExample(CcArchivesExample example);

    CcArchives selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CcArchives record, @Param("example") CcArchivesExample example);

    int updateByExample(@Param("record") CcArchives record, @Param("example") CcArchivesExample example);

    int updateByPrimaryKeySelective(CcArchives record);

    int updateByPrimaryKey(CcArchives record);
}