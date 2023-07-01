package com.sifu.sfcc.service.impl;

import com.github.pagehelper.PageHelper;
import com.sifu.sfcc.mapper.CcEnterpriseMapper;
import com.sifu.sfcc.model.CcEnterprise;
import com.sifu.sfcc.model.CcEnterpriseExample;
import com.sifu.sfcc.service.CcEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 企业信息管理Service实现类
 * Created by sifu on 2018/4/26.
 */
@Service
public class CcEnterpriseServiceImpl implements CcEnterpriseService {
    @Autowired
    private CcEnterpriseMapper enterpriseMapper;
    @Override
    public List<CcEnterprise> list(String name , Integer pageSize , Integer pageNum) {
        PageHelper.startPage(pageNum , pageSize);
        CcEnterpriseExample example = new CcEnterpriseExample();
        CcEnterpriseExample.Criteria criteria = example.createCriteria();
        if(name != null && !"".equals(name)){
            criteria.andEnterpriseTitleLike("%" +name+ "%");
        }

        return enterpriseMapper.selectByExample(example);
    }

    @Override
    public int add(CcEnterprise member) {
        return enterpriseMapper.insertSelective(member);
    }

    @Override
    public int update(CcEnterprise member) {

        return enterpriseMapper.updateByPrimaryKeySelective(member);
    }

    @Override
    public int delete(Long id) {
        return enterpriseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public CcEnterprise get(Long id) {
        return enterpriseMapper.selectByPrimaryKey(id);
    }
}
