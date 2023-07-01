package com.sifu.sfcc.service;

import com.sifu.sfcc.model.UmsMemberLevel;

import java.util.List;

/**
 * 会员等级管理Service
 * Created by sifu on 2018/4/26.
 */
public interface UmsMemberLevelService {
    /**
     * 获取所有会员登录
     */
    List<UmsMemberLevel> list(String levelName , Integer pageSize , Integer pageNum);

    int add(UmsMemberLevel memberLevel);

    int update(UmsMemberLevel memberLevel);

    int delete(Long id);

    UmsMemberLevel get(Long id);
}
