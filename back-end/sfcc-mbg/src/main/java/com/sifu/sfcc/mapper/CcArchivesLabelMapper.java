package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.CcArchivesLabel;
import com.sifu.sfcc.model.CcArchivesLabelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CcArchivesLabelMapper {
    long countByExample(CcArchivesLabelExample example);

    int deleteByExample(CcArchivesLabelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CcArchivesLabel record);

    int insertSelective(CcArchivesLabel record);

    List<CcArchivesLabel> selectByExample(CcArchivesLabelExample example);

    CcArchivesLabel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CcArchivesLabel record, @Param("example") CcArchivesLabelExample example);

    int updateByExample(@Param("record") CcArchivesLabel record, @Param("example") CcArchivesLabelExample example);

    int updateByPrimaryKeySelective(CcArchivesLabel record);

    int updateByPrimaryKey(CcArchivesLabel record);
}