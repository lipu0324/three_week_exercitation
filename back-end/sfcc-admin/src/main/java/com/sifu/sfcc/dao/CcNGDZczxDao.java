package com.sifu.sfcc.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CcNGDZczxDao {


    int queryZc_szc(@Param("bmlxmc") String bmlxmc);

    int zc_szc_ds(@Param("bmlxmc") String bmlxmc, @Param("sys_location") String sys_location);

    int zc_szc_ds_bt(@Param("bmlxmc") String bmlxmc, @Param("sys_location") String sys_location, @Param("bt") String bt);

    int zc_zczx(@Param("sys_location") String sys_location, @Param("bt") String bt);
}
