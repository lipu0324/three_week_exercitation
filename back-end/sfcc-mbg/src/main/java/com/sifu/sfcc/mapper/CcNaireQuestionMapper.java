package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.CcNaireQuestion;
import com.sifu.sfcc.model.CcNaireQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CcNaireQuestionMapper {
    long countByExample(CcNaireQuestionExample example);

    int deleteByExample(CcNaireQuestionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CcNaireQuestion record);

    int insertSelective(CcNaireQuestion record);

    List<CcNaireQuestion> selectByExample(CcNaireQuestionExample example);

    CcNaireQuestion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CcNaireQuestion record, @Param("example") CcNaireQuestionExample example);

    int updateByExample(@Param("record") CcNaireQuestion record, @Param("example") CcNaireQuestionExample example);

    int updateByPrimaryKeySelective(CcNaireQuestion record);

    int updateByPrimaryKey(CcNaireQuestion record);
}