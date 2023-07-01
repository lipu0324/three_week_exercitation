package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.XmkAreaCity;
import com.sifu.sfcc.model.XmkAreaCityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XmkAreaCityMapper {
    long countByExample(XmkAreaCityExample example);

    int deleteByExample(XmkAreaCityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(XmkAreaCity record);

    int insertSelective(XmkAreaCity record);

    List<XmkAreaCity> selectByExample(XmkAreaCityExample example);

    XmkAreaCity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") XmkAreaCity record, @Param("example") XmkAreaCityExample example);

    int updateByExample(@Param("record") XmkAreaCity record, @Param("example") XmkAreaCityExample example);

    int updateByPrimaryKeySelective(XmkAreaCity record);

    int updateByPrimaryKey(XmkAreaCity record);
}