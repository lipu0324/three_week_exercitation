package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.XmkKeyword;
import com.sifu.sfcc.model.XmkKeywordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XmkKeywordMapper {
    long countByExample(XmkKeywordExample example);

    int deleteByExample(XmkKeywordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(XmkKeyword record);

    int insertSelective(XmkKeyword record);

    List<XmkKeyword> selectByExample(XmkKeywordExample example);

    XmkKeyword selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") XmkKeyword record, @Param("example") XmkKeywordExample example);

    int updateByExample(@Param("record") XmkKeyword record, @Param("example") XmkKeywordExample example);

    int updateByPrimaryKeySelective(XmkKeyword record);

    int updateByPrimaryKey(XmkKeyword record);
}