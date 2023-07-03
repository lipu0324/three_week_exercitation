package com.sifu.sfcc.dao;

import com.sifu.sfcc.model.UmsMenu;
import com.sifu.sfcc.model.UmsResource;
import com.sifu.sfcc.model.UmsRole;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义后台角色管理Dao
 * Created by sifu on 2020/2/2.
 */
public interface UmsRoleDao {
    /**
     * 根据后台用户ID获取菜单
     */
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);
    /**
     * 根据角色ID获取菜单
     */
    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);
    /**
     * 根据角色ID获取资源
     */
    List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);
    
    List<UmsRole> list(@Param("keyword") String keyword);
}
