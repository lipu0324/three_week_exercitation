package com.sifu.sfcc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sifu.sfcc.dao.CcNGDXmzxDao;
import com.sifu.sfcc.dto.ProjectParam;
import com.sifu.sfcc.service.CcNGDService;
import com.sifu.sfcc.service.CcNGDXmzxService;
import com.sifu.sfcc.service.XmkProjectService;
import com.sifu.sfcc.util.NGDSendHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CcNGDXmzxServiceImpl implements CcNGDXmzxService {
    //39.103.236.61获取电话号
    //http://39.105.160.25业务系统
    @Autowired
    private CcNGDXmzxDao dao;

    @Autowired
    private XmkProjectService xmkProjectService;


    @Autowired
    private CcNGDService ccNGDService;

    @Override
    public Map getProjectInfo(JSONObject context,String session_id,String phone) {
        Map map = new HashMap();
        JSONObject diZhiJson = context.getJSONObject("dizhi");
        String province = diZhiJson.getString("province");
        String city = diZhiJson.getString("city");
        String area = diZhiJson.getString("area");
        String st_xmzl = context.getString("st_xmzl");
        String tuijian  = context.getString("tuijian");
        String keyword = "";
        //查询需要传入的参数行业
        diZhiJson.put("projectType",st_xmzl);
        StringBuffer stringBuffer = new StringBuffer("");
        if(StringUtils.isEmpty(area)){
            stringBuffer.append(province).append(city);
            keyword = keyword + city;
        }else{
            stringBuffer.append(province).append(city).append(area);
            keyword = keyword + area;
        }
        System.out.println("保存项目成功");
        //查询数量
        ProjectParam total = xmkProjectService.getTotal(diZhiJson.toString());
        //推荐最多的两个项目
        ProjectParam recommend = xmkProjectService.getRecommend(diZhiJson.toString(),2);
        if(total.getTotalCount() <= 0){
            map.put("res",stringBuffer + st_xmzl + "行业没有找到项目，请问还有什么可以帮助您？");
        }else{
//            map.put("res",stringBuffer + st_xmzl + "行业共找到"+total.getTotalCount()+"个项目；"+"其中咨询人数最多的项目是:"+ recommend.getProjectName() + ",您可以通过说出《发短信》，我们会将相关内容以短信形式发送到您的手机。或者说出您想要了解哪个项目？");

            map.put("res",stringBuffer + st_xmzl + "行业共找到"+total.getTotalCount()+"个项目；"+"其中咨询人数最多的项目是:"+ recommend.getProjectName() + ",稍后会以短信的形式发送到您的手机。");
            ccNGDService.saveQ(phone,"1",stringBuffer + st_xmzl,session_id);
            map.put("keyword",stringBuffer + st_xmzl);
        }
        return map;
    }


}
