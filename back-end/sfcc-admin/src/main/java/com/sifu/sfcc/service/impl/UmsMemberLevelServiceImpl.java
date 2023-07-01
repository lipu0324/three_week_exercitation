package com.sifu.sfcc.service.impl;

import com.github.pagehelper.PageHelper;
import com.sifu.sfcc.mapper.UmsMemberLevelMapper;
import com.sifu.sfcc.model.UmsMemberLevel;
import com.sifu.sfcc.model.UmsMemberLevelExample;
import com.sifu.sfcc.service.UmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员等级管理Service实现类
 * Created by sifu on 2018/4/26.
 */
@Service
public class UmsMemberLevelServiceImpl implements UmsMemberLevelService {
    @Autowired
    private UmsMemberLevelMapper memberLevelMapper;
    @Override
    public List<UmsMemberLevel> list(String levelName , Integer pageSize , Integer pageNum) {
        PageHelper.startPage(pageNum , pageSize);
        UmsMemberLevelExample example = new UmsMemberLevelExample();
        UmsMemberLevelExample.Criteria criteria = example.createCriteria();
        if(levelName != null && !"".equals(levelName)){
            criteria.andNameLike("%" +levelName+ "%");
        }

        return memberLevelMapper.selectByExample(example);
    }

    @Override
    public int add(UmsMemberLevel memberLevel) {
        return memberLevelMapper.insertSelective(memberLevel);
    }

    @Override
    public int update(UmsMemberLevel memberLevel) {

        return memberLevelMapper.updateByPrimaryKeySelective(memberLevel);
    }

    @Override
    public int delete(Long id) {
        return memberLevelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UmsMemberLevel get(Long id) {
        return memberLevelMapper.selectByPrimaryKey(id);
    }
}
