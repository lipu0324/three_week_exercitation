package com.sifu.sfcc.dao;

import com.sifu.sfcc.dto.AdminParam;
import org.apache.ibatis.annotations.Param;
/**
 * 部门管理 数据层
 * 
 * @author wuying
 */
public interface UmsAdminDao
{
    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public AdminParam selectAdminById(@Param("contentId")long id);

   
}
