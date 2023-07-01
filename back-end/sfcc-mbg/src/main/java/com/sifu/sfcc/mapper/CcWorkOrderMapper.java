package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.CcWorkOrder;
import com.sifu.sfcc.model.CcWorkOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CcWorkOrderMapper {
    long countByExample(CcWorkOrderExample example);

    int deleteByExample(CcWorkOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CcWorkOrder record);

    int insertSelective(CcWorkOrder record);

    List<CcWorkOrder> selectByExampleWithBLOBs(CcWorkOrderExample example);

    List<CcWorkOrder> selectByExample(CcWorkOrderExample example);

    CcWorkOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CcWorkOrder record, @Param("example") CcWorkOrderExample example);

    int updateByExampleWithBLOBs(@Param("record") CcWorkOrder record, @Param("example") CcWorkOrderExample example);

    int updateByExample(@Param("record") CcWorkOrder record, @Param("example") CcWorkOrderExample example);

    int updateByPrimaryKeySelective(CcWorkOrder record);

    int updateByPrimaryKeyWithBLOBs(CcWorkOrder record);

    int updateByPrimaryKey(CcWorkOrder record);
}