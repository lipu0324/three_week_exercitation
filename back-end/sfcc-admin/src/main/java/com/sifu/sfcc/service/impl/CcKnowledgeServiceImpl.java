package com.sifu.sfcc.service.impl;

import com.github.pagehelper.PageHelper;
import com.sifu.sfcc.mapper.CcKnowledgeMapper;
import com.sifu.sfcc.model.CcKnowledge;
import com.sifu.sfcc.model.CcKnowledgeExample;
import com.sifu.sfcc.service.CcKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 知识库管理Service实现类
 * Created by sifu on 2018/4/26.
 */
@Service
public class CcKnowledgeServiceImpl implements CcKnowledgeService {
    @Autowired
    private CcKnowledgeMapper knowledgeMapper;
    
    @Override
    public List<CcKnowledge> list(String question , Integer pageSize , Integer pageNum) {
        PageHelper.startPage(pageNum , pageSize);
        CcKnowledgeExample example = new CcKnowledgeExample();
        CcKnowledgeExample.Criteria criteria = example.createCriteria();
        if(question != null && !"".equals(question)){
        	criteria.andQuestionLike("%" +question+ "%");
        }

        return knowledgeMapper.selectByExample(example);
    }

    @Override
    public int add(CcKnowledge label) {
        return knowledgeMapper.insertSelective(label);
    }

    @Override
    public int update(CcKnowledge label) {

        return knowledgeMapper.updateByPrimaryKeySelective(label);
    }

    @Override
    public int delete(Long id) {
        return knowledgeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public CcKnowledge get(Long id) {
        return knowledgeMapper.selectByPrimaryKey(id);
    }

	@Override
	public List<CcKnowledge> list(String question) {
		CcKnowledgeExample example = new CcKnowledgeExample();
        CcKnowledgeExample.Criteria criteria = example.createCriteria();
        if(question != null && !"".equals(question)){
        	criteria.andQuestionLike("%" +question+ "%");
        }
		return knowledgeMapper.selectByExample(example);
	}

	@Override
	public long total() {
		CcKnowledgeExample example = new CcKnowledgeExample();
		return knowledgeMapper.countByExample(example);
	}
}
