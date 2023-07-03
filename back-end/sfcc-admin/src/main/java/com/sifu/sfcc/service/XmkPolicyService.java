package com.sifu.sfcc.service;

import com.sifu.sfcc.dto.PolicyParam;
import com.sifu.sfcc.dto.ProjectParam;
import com.sifu.sfcc.model.XmkPolicy;

import java.util.List;

public interface XmkPolicyService {
    boolean exportPolicy(Integer sBmType, Integer sType, Integer sOrgan);

    String importXmkPolicy();

    boolean clearPolicy();

    List<XmkPolicy> getPolicyByTitle(String title);
    
    PolicyParam getPolicyCount(String location);
    
    PolicyParam getPolicyRecommend(String location,Integer pageNum);
}
