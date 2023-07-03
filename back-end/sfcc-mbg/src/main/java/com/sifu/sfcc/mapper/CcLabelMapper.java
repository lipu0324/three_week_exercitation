package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.CcLabel;
import com.sifu.sfcc.model.CcLabelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CcLabelMapper {
    long countByExample(CcLabelExample example);

    int deleteByExample(CcLabelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CcLabel record);

    int insertSelective(CcLabel record);

    List<CcLabel> selectByExample(CcLabelExample example);

    CcLabel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CcLabel record, @Param("example") CcLabelExample example);

    int updateByExample(@Param("record") CcLabel record, @Param("example") CcLabelExample example);

    int updateByPrimaryKeySelective(CcLabel record);

    int updateByPrimaryKey(CcLabel record);
}