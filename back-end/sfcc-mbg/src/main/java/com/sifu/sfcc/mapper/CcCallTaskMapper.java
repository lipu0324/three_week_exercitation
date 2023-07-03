package com.sifu.sfcc.mapper;

import com.sifu.sfcc.model.CcCallTask;
import com.sifu.sfcc.model.CcCallTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CcCallTaskMapper {
    long countByExample(CcCallTaskExample example);

    int deleteByExample(CcCallTaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CcCallTask record);

    int insertSelective(CcCallTask record);

    List<CcCallTask> selectByExample(CcCallTaskExample example);

    CcCallTask selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CcCallTask record, @Param("example") CcCallTaskExample example);

    int updateByExample(@Param("record") CcCallTask record, @Param("example") CcCallTaskExample example);

    int updateByPrimaryKeySelective(CcCallTask record);

    int updateByPrimaryKey(CcCallTask record);
}