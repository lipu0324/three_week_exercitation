package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.CcNaireAnswer;
import com.sifu.sfcc.model.CcNaireAnswerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CcNaireAnswerMapper {
    long countByExample(CcNaireAnswerExample example);

    int deleteByExample(CcNaireAnswerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CcNaireAnswer record);

    int insertSelective(CcNaireAnswer record);

    List<CcNaireAnswer> selectByExample(CcNaireAnswerExample example);

    CcNaireAnswer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CcNaireAnswer record, @Param("example") CcNaireAnswerExample example);

    int updateByExample(@Param("record") CcNaireAnswer record, @Param("example") CcNaireAnswerExample example);

    int updateByPrimaryKeySelective(CcNaireAnswer record);

    int updateByPrimaryKey(CcNaireAnswer record);
}