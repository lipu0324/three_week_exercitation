package com.sifu.sfcc.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CcNGDXmzxDao {


    int xm_zx(@Param("sys_location_substr") String sys_location_substr, @Param("xm_tzfs") String xm_tzfs, @Param("xm_sshy") String xm_sshy, @Param("sys_money") String sys_money);

    List<String> queryTzfs();

    List<String> querySshy();
}
