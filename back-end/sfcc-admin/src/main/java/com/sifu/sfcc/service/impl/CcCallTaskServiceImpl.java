package com.sifu.sfcc.service.impl;

import com.github.pagehelper.PageHelper;
import com.sifu.sfcc.dao.CcCallTaskDao;
import com.sifu.sfcc.dto.CcCallTaskParam;
import com.sifu.sfcc.mapper.CcCallTaskMapper;
import com.sifu.sfcc.model.CcCallTask;
import com.sifu.sfcc.model.CcCallTaskExample;
import com.sifu.sfcc.service.CcCallTaskService;
import com.sifu.sfcc.service.UmsAdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 问卷系统管理Service实现类
 * Created by sifu on 2018/4/26.
 */
@Service
public class CcCallTaskServiceImpl implements CcCallTaskService {
    @Autowired
    private CcCallTaskMapper taskMapper;
    
    @Autowired
    private CcCallTaskDao taskDao;
    
    @Autowired
    private UmsAdminService adminService;

	@Override
	public List<CcCallTaskParam> list(String title, String status, String startTime, String endTime, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum , pageSize);
//		CcCallTaskExample example = new CcCallTaskExample();
//		CcCallTaskExample.Criteria criteria = example.createCriteria();
//        if(title != null && !"".equals(title)){
//        	criteria.andTitleLike("%" +title+ "%");
//        }
//        
//        if(startTime != null && !"".equals(startTime)){
//        	criteria.andCreatedTimeBetween(startTime, endTime);
//        }
        
        return taskDao.list(title, status, startTime, endTime);
	}

	@Override
	public int add(CcCallTask naire) {
		naire.setCreatedBy(adminService.getCurrentUserAdmin().getId());
		naire.setCreatedTime(new Date());
		return taskMapper.insertSelective(naire);
	}

	@Override
	public int update(CcCallTask naire) {
		return taskMapper.updateByPrimaryKeySelective(naire);
	}

	@Override
	public int delete(Long id) {
		return taskMapper.deleteByPrimaryKey(id);
	}

	@Override
	public CcCallTask get(Long id) {
		return taskMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateStatus(CcCallTask naire) {
		if(naire.getStatus() == 1) {
			//判断当前是否有正在进行的其他任务
			CcCallTaskExample example = new CcCallTaskExample();
			CcCallTaskExample.Criteria criteria = example.createCriteria();
			criteria.andStatusEqualTo(1);
			List<CcCallTask> ct = taskMapper.selectByExample(example);
			if(ct.size() >= 1) {
				naire.setStatus(0);
				taskMapper.updateByPrimaryKeySelective(naire);
				return 0;
			}
		}
		
		return taskMapper.updateByPrimaryKeySelective(naire);
	}

    
}
