package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.CcKnowledge;
import com.sifu.sfcc.model.CcKnowledgeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CcKnowledgeMapper {
    long countByExample(CcKnowledgeExample example);

    int deleteByExample(CcKnowledgeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CcKnowledge record);

    int insertSelective(CcKnowledge record);

    List<CcKnowledge> selectByExample(CcKnowledgeExample example);

    CcKnowledge selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CcKnowledge record, @Param("example") CcKnowledgeExample example);

    int updateByExample(@Param("record") CcKnowledge record, @Param("example") CcKnowledgeExample example);

    int updateByPrimaryKeySelective(CcKnowledge record);

    int updateByPrimaryKey(CcKnowledge record);
}