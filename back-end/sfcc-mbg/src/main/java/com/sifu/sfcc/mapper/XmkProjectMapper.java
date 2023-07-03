package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.XmkProject;
import com.sifu.sfcc.model.XmkProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XmkProjectMapper {
    long countByExample(XmkProjectExample example);

    int deleteByExample(XmkProjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(XmkProject record);

    int insertSelective(XmkProject record);

    List<XmkProject> selectByExampleWithBLOBs(XmkProjectExample example);

    List<XmkProject> selectByExample(XmkProjectExample example);

    XmkProject selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") XmkProject record, @Param("example") XmkProjectExample example);

    int updateByExampleWithBLOBs(@Param("record") XmkProject record, @Param("example") XmkProjectExample example);

    int updateByExample(@Param("record") XmkProject record, @Param("example") XmkProjectExample example);

    int updateByPrimaryKeySelective(XmkProject record);

    int updateByPrimaryKeyWithBLOBs(XmkProject record);

    int updateByPrimaryKey(XmkProject record);
}