package com.sifu.sfcc.service.impl;

import com.github.pagehelper.PageHelper;
import com.sifu.sfcc.mapper.CcLabelMapper;
import com.sifu.sfcc.model.CcLabel;
import com.sifu.sfcc.model.CcLabelExample;
import com.sifu.sfcc.service.CcLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 标签管理Service实现类
 * Created by sifu on 2018/4/26.
 */
@Service
public class CcLabelServiceImpl implements CcLabelService {
    @Autowired
    private CcLabelMapper labelMapper;
    @Override
    public List<CcLabel> list(String name , Integer status, Integer pageSize , Integer pageNum) {
        PageHelper.startPage(pageNum , pageSize);
        CcLabelExample example = new CcLabelExample();
        CcLabelExample.Criteria criteria = example.createCriteria();
        if(name != null && !"".equals(name)){
            criteria.andNameLike("%" +name+ "%");
        }
        
        if(status != 2 ){
            criteria.andStatusEqualTo(status);
        }

        return labelMapper.selectByExample(example);
    }

    @Override
    public int add(CcLabel label) {
    	label.setCreateTime(new Date());
        return labelMapper.insertSelective(label);
    }

    @Override
    public int update(CcLabel label) {

        return labelMapper.updateByPrimaryKeySelective(label);
    }

    @Override
    public int delete(Long id) {
        return labelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public CcLabel get(Long id) {
        return labelMapper.selectByPrimaryKey(id);
    }

	@Override
	public int updateStatus(Long id, Integer status) {
		CcLabel label = new CcLabel();
		label.setStatus(status);
		label.setId(id);
		return labelMapper.updateByPrimaryKeySelective(label);
		
	}

	@Override
	public int batchDelete(List<Long> ids) {
		CcLabelExample example = new CcLabelExample();
		example.createCriteria().andIdIn(ids);
		return labelMapper.deleteByExample(example);
	}
}
