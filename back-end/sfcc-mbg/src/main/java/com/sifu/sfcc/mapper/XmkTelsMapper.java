package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.XmkTels;
import com.sifu.sfcc.model.XmkTelsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XmkTelsMapper {
    long countByExample(XmkTelsExample example);

    int deleteByExample(XmkTelsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XmkTels record);

    int insertSelective(XmkTels record);

    List<XmkTels> selectByExample(XmkTelsExample example);

    XmkTels selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XmkTels record, @Param("example") XmkTelsExample example);

    int updateByExample(@Param("record") XmkTels record, @Param("example") XmkTelsExample example);

    int updateByPrimaryKeySelective(XmkTels record);

    int updateByPrimaryKey(XmkTels record);
}