package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.CcNaire;
import com.sifu.sfcc.model.CcNaireExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CcNaireMapper {
    long countByExample(CcNaireExample example);

    int deleteByExample(CcNaireExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CcNaire record);

    int insertSelective(CcNaire record);

    List<CcNaire> selectByExample(CcNaireExample example);

    CcNaire selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CcNaire record, @Param("example") CcNaireExample example);

    int updateByExample(@Param("record") CcNaire record, @Param("example") CcNaireExample example);

    int updateByPrimaryKeySelective(CcNaire record);

    int updateByPrimaryKey(CcNaire record);
}