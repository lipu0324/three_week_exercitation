package com.sifu.sfcc.service.impl;

import com.github.pagehelper.PageHelper;
import com.sifu.sfcc.dao.CcArchivesContentDao;
import com.sifu.sfcc.mapper.CcArchivesContentMapper;
import com.sifu.sfcc.model.CcArchivesContent;
import com.sifu.sfcc.model.CcArchivesContentExample;
import com.sifu.sfcc.service.CcArchivesContentService;
import com.sifu.sfcc.service.UmsAdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 知识库管理Service实现类
 * Created by sifu on 2021/11/12.
 */
@Service
public class CcArchivesContentServiceImpl implements CcArchivesContentService {
    @Autowired
    private CcArchivesContentMapper archivesMapper;
    
    @Autowired
    private CcArchivesContentDao contentDao;
    
    @Autowired
    private UmsAdminService adminService;
    
    @Override
    public List<CcArchivesContent> list(long archivesId, Integer pageSize , Integer pageNum) {
        PageHelper.startPage(pageNum , pageSize);
        CcArchivesContentExample example = new CcArchivesContentExample();
        CcArchivesContentExample.Criteria criteria = example.createCriteria();
        if(archivesId != 0){
        	criteria.andArchivesIdEqualTo(archivesId);
        }

        return archivesMapper.selectByExample(example);
    }

    @Override
    public int add(CcArchivesContent content) {
    	content.setCreatedTime(new Date());
    	content.setCreatedBy(adminService.getCurrentUserAdmin().getId());
        return archivesMapper.insertSelective(content);
    }

    @Override
    public int update(CcArchivesContent label) {
    	label.setCreatedBy(adminService.getCurrentUserAdmin().getId());
        return archivesMapper.updateByPrimaryKeySelective(label);
    }

    @Override
    public int delete(Long id) {
        return archivesMapper.deleteByPrimaryKey(id);
    }

	@Override
	public List<CcArchivesContent> list(long archivesId) {
		CcArchivesContentExample example = new CcArchivesContentExample();
        CcArchivesContentExample.Criteria criteria = example.createCriteria();
        if(archivesId != 0){
        	criteria.andArchivesIdEqualTo(archivesId);
        }
        example.setOrderByClause("created_time desc");
        return archivesMapper.selectByExample(example);
	}

	@Override
	public int updateBatch(List<CcArchivesContent> content) {
		
		return contentDao.updateBatch(content);
	}
	
}
