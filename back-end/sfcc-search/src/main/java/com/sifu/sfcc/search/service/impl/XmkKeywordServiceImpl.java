package com.sifu.sfcc.search.service.impl;

import com.sifu.sfcc.mapper.XmkKeywordMapper;
import com.sifu.sfcc.search.service.XmkKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XmkKeywordServiceImpl implements XmkKeywordService {

    @Autowired
    XmkKeywordMapper xmkKeywordMapper;
    @Override
    public String getKeywordStr(String keyword) {
        String[] ids = keyword.split(",");
        StringBuilder sb = new StringBuilder();
        if(null != ids && ids.length > 0){
            for(String id : ids){
                sb.append(xmkKeywordMapper.selectByPrimaryKey(Long.valueOf(id)).getKeyword());
            }
        }
        System.out.println("getKeywordStr = " + sb.toString());
        return sb.toString();
    }

}
