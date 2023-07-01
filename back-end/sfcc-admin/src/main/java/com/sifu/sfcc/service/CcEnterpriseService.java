package com.sifu.sfcc.service;

import com.sifu.sfcc.model.CcEnterprise;
import java.util.List;

/**
 * 企业信息管理Service
 * Created by sifu on 2021/10/21.
 */
public interface CcEnterpriseService {
    /**
     * 获取所有企业信息
     */
    List<CcEnterprise> list(String name , Integer pageSize , Integer pageNum);

    int add(CcEnterprise member);

    int update(CcEnterprise member);

    int delete(Long id);

    CcEnterprise get(Long id);
}
