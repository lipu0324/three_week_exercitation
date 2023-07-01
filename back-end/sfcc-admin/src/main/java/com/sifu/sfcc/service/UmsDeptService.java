package com.sifu.sfcc.service;

import java.util.List;

import com.sifu.sfcc.model.UmsDept;


/**
 * 部门管理 服务层
 * 
 * @author ruoyi
 */
public interface UmsDeptService 
{
    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public List<UmsDept> selectDeptList(String deptName,String deptPhone,String leader,String phone,String city, String status,Integer pageSize,Integer pageNum);

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
     * @param dept 部门信息
     * @return 结果
     */
    public String checkDeptNameUnique(UmsDept dept);

    /**
     * 新增保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int insertDept(UmsDept dept);

    /**
     * 修改保存部门信息
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
    
    /**
     * 批量封禁部门
     * 
     * @param ids 部门ID
     * @return 结果
     */
    int batchBan(List<Long> ids);
    
    long total();
}
