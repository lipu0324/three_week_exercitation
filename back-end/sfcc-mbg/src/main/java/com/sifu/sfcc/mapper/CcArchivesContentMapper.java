package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.CcArchivesContent;
import com.sifu.sfcc.model.CcArchivesContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CcArchivesContentMapper {
    long countByExample(CcArchivesContentExample example);

    int deleteByExample(CcArchivesContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CcArchivesContent record);

    int insertSelective(CcArchivesContent record);

    List<CcArchivesContent> selectByExample(CcArchivesContentExample example);

    CcArchivesContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CcArchivesContent record, @Param("example") CcArchivesContentExample example);

    int updateByExample(@Param("record") CcArchivesContent record, @Param("example") CcArchivesContentExample example);

    int updateByPrimaryKeySelective(CcArchivesContent record);

    int updateByPrimaryKey(CcArchivesContent record);
}