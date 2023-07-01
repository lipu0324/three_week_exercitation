package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.XmkPolicy;
import com.sifu.sfcc.model.XmkPolicyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XmkPolicyMapper {
    long countByExample(XmkPolicyExample example);

    int deleteByExample(XmkPolicyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(XmkPolicy record);

    int insertSelective(XmkPolicy record);

    List<XmkPolicy> selectByExampleWithBLOBs(XmkPolicyExample example);

    List<XmkPolicy> selectByExample(XmkPolicyExample example);

    XmkPolicy selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") XmkPolicy record, @Param("example") XmkPolicyExample example);

    int updateByExampleWithBLOBs(@Param("record") XmkPolicy record, @Param("example") XmkPolicyExample example);

    int updateByExample(@Param("record") XmkPolicy record, @Param("example") XmkPolicyExample example);

    int updateByPrimaryKeySelective(XmkPolicy record);

    int updateByPrimaryKeyWithBLOBs(XmkPolicy record);

    int updateByPrimaryKey(XmkPolicy record);
}