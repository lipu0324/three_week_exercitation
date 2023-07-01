package com.sifu.sfcc.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.sifu.sfcc.model.UmsDept;

/**
 * 部门管理 数据层
 * 
 * @author wuying
 */
public interface UmsDeptDao
{
    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public List<UmsDept> selectDeptList(String deptName,String deptPhone,String leader,String phone,String city,String status,Integer pageSize,Integer pageNum);

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    public UmsDept selectDeptById(Long deptId);

    /**
     * 校验部门名称是否唯一
     * 
     * @param deptName 部门名称
     * @return 结果
     */
    public UmsDept checkDeptNameUnique(@Param("deptName") String deptName);

    /**
     * 新增部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int insertDept(UmsDept dept);

    /**
     * 修改部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int updateDept(UmsDept dept);

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);
}
