package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.XmkGarden;
import com.sifu.sfcc.model.XmkGardenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XmkGardenMapper {
    long countByExample(XmkGardenExample example);

    int deleteByExample(XmkGardenExample example);

    int deleteByPrimaryKey(Long id);

    int insert(XmkGarden record);

    int insertSelective(XmkGarden record);

    List<XmkGarden> selectByExample(XmkGardenExample example);

    XmkGarden selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") XmkGarden record, @Param("example") XmkGardenExample example);

    int updateByExample(@Param("record") XmkGarden record, @Param("example") XmkGardenExample example);

    int updateByPrimaryKeySelective(XmkGarden record);

    int updateByPrimaryKey(XmkGarden record);
}