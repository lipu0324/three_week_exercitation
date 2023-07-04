package com.sifu.sfcc.service.impl;

import com.github.pagehelper.PageHelper;
import com.sifu.sfcc.mapper.CcLabelMapper;
import com.sifu.sfcc.model.CcLabel;
import com.sifu.sfcc.model.CcLabelExample;
import com.sifu.sfcc.service.CcLabelService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    public HashMap<String, Object> list(String name ) {
        CcLabelExample ccLabelExample = new CcLabelExample();
        if (StringUtils.hasText(name)){
            ccLabelExample.createCriteria().andNameLike("%"+name+"%");

        }
        List<CcLabel> ccLabels =labelMapper.selectByExample(ccLabelExample);
        labelMapper.countByExample(ccLabelExample);
        long l =labelMapper.countByExample(ccLabelExample);
        return new HashMap<String,Object>(){{
            put("total",l);
            put("list",ccLabels);
        }};
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
	public int batchDelete(ArrayList<Long> ids) {
		CcLabelExample ccLabelExample = new CcLabelExample();
        CcLabelExample.Criteria criteria = ccLabelExample.createCriteria();
        criteria.andIdIn(ids);
		return labelMapper.deleteByExample(ccLabelExample);
	}
}
