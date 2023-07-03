package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.CcArchivesCustomer;
import com.sifu.sfcc.model.CcArchivesCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CcArchivesCustomerMapper {
    long countByExample(CcArchivesCustomerExample example);

    int deleteByExample(CcArchivesCustomerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CcArchivesCustomer record);

    int insertSelective(CcArchivesCustomer record);

    List<CcArchivesCustomer> selectByExample(CcArchivesCustomerExample example);

    CcArchivesCustomer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CcArchivesCustomer record, @Param("example") CcArchivesCustomerExample example);

    int updateByExample(@Param("record") CcArchivesCustomer record, @Param("example") CcArchivesCustomerExample example);

    int updateByPrimaryKeySelective(CcArchivesCustomer record);

    int updateByPrimaryKey(CcArchivesCustomer record);
}