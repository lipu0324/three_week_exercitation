package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.UmsDept;
import com.sifu.sfcc.model.UmsDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsDeptMapper {
    long countByExample(UmsDeptExample example);

    int deleteByExample(UmsDeptExample example);

    int deleteByPrimaryKey(Long deptId);

    int insert(UmsDept record);

    int insertSelective(UmsDept record);

    List<UmsDept> selectByExample(UmsDeptExample example);

    UmsDept selectByPrimaryKey(Long deptId);

    int updateByExampleSelective(@Param("record") UmsDept record, @Param("example") UmsDeptExample example);

    int updateByExample(@Param("record") UmsDept record, @Param("example") UmsDeptExample example);

    int updateByPrimaryKeySelective(UmsDept record);

    int updateByPrimaryKey(UmsDept record);
}