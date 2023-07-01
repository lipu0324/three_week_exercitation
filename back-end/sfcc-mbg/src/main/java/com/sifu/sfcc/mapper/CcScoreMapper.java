package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.CcScore;
import com.sifu.sfcc.model.CcScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CcScoreMapper {
    long countByExample(CcScoreExample example);

    int deleteByExample(CcScoreExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CcScore record);

    int insertSelective(CcScore record);

    List<CcScore> selectByExample(CcScoreExample example);

    CcScore selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CcScore record, @Param("example") CcScoreExample example);

    int updateByExample(@Param("record") CcScore record, @Param("example") CcScoreExample example);

    int updateByPrimaryKeySelective(CcScore record);

    int updateByPrimaryKey(CcScore record);
}