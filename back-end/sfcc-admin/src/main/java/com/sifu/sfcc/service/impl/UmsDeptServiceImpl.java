package com.sifu.sfcc.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.github.pagehelper.PageHelper;
import com.sifu.sfcc.dao.UmsDeptDao;
import com.sifu.sfcc.mapper.UmsDeptMapper;
import com.sifu.sfcc.model.UmsDept;
import com.sifu.sfcc.model.UmsDeptExample;
import com.sifu.sfcc.service.UmsDeptService;

/**
 * 部门管理 服务实现
 * 
 * @author wuying
 */
@Service
public class UmsDeptServiceImpl  implements UmsDeptService
{
    @Autowired
    private UmsDeptDao deptDao;
    
    @Autowired
    private UmsDeptMapper deptMapper;

    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @Override
    public List<UmsDept> selectDeptList(String deptName,String deptPhone,String leader,String phone,String city,String status,Integer pageSize,Integer pageNum)
    {
    	PageHelper.startPage(pageNum , pageSize);
        return deptDao.selectDeptList(deptName,deptPhone,leader,phone,city,status,pageSize,pageNum);
    }
    

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public UmsDept selectDeptById(Long deptId)
    {
        return deptDao.selectDeptById(deptId);
    }


    /**
     * 校验部门名称是否唯一
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public String checkDeptNameUnique(UmsDept dept)
    {
        Long deptId = ObjectUtils.isEmpty(dept.getDeptId()) ? -1L : dept.getDeptId();
        UmsDept info = deptDao.checkDeptNameUnique(dept.getDeptName());
        if (!ObjectUtils.isEmpty(info) && info.getDeptId().longValue() != deptId.longValue())
        {
            return "1";
        }
        return "0";
    }

    /**
     * 新增保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int insertDept(UmsDept dept)
    {
    	dept.setCreateTime(new Date());
        return deptDao.insertDept(dept);
    }

    /**
     * 修改保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int updateDept(UmsDept dept)
    {
    	dept.setUpdateTime(new Date());
        int result = deptDao.updateDept(dept);
        return result;
    }

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public int deleteDeptById(Long deptId)
    {
        return deptDao.deleteDeptById(deptId);
    }


	@Override
	public int batchBan(List<Long> ids) {
		UmsDeptExample example = new UmsDeptExample();
		example.createCriteria().andDeptIdIn(ids);
		UmsDept um = new UmsDept();
		um.setStatus("1");
		return deptMapper.updateByExampleSelective(um, example);
	}


	@Override
	public long total() {
		// TODO Auto-generated method stub
		UmsDeptExample example = new UmsDeptExample();
		return deptMapper.countByExample(example);
	}

}
