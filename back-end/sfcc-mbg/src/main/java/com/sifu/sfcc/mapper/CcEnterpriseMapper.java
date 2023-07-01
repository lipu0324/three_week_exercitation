package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.CcEnterprise;
import com.sifu.sfcc.model.CcEnterpriseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CcEnterpriseMapper {
    long countByExample(CcEnterpriseExample example);

    int deleteByExample(CcEnterpriseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CcEnterprise record);

    int insertSelective(CcEnterprise record);

    List<CcEnterprise> selectByExample(CcEnterpriseExample example);

    CcEnterprise selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CcEnterprise record, @Param("example") CcEnterpriseExample example);

    int updateByExample(@Param("record") CcEnterprise record, @Param("example") CcEnterpriseExample example);

    int updateByPrimaryKeySelective(CcEnterprise record);

    int updateByPrimaryKey(CcEnterprise record);
}