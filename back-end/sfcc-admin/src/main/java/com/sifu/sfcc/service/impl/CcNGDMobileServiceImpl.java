package com.sifu.sfcc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sifu.sfcc.dto.CcScoreParam;
import com.sifu.sfcc.model.XmkTels;
import com.sifu.sfcc.service.CcNGDMobileService;
import com.sifu.sfcc.service.CcNGDService;
import com.sifu.sfcc.service.CcSessionService;
import com.sifu.sfcc.service.XmkTelsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CcNGDMobileServiceImpl implements CcNGDMobileService {

    @Autowired
    private XmkTelsService xmkTelsService;

    @Autowired
    private CcSessionService ccSessionService;

    @Autowired
    private CcNGDService ccNGDService;

    @Override
    public Map getTels(JSONObject context,String session_id,String phone) {
        Map map = new HashMap();
        JSONObject diZhiJson = context.getJSONObject("dizhi");
        String diZhi = diZhiJson.toJSONString();
        String city = diZhiJson.getString("city");
        String tel_ds = context.getString("tel_ds");
        //1、已知地市厅局电话时
        if (!StringUtils.isEmpty(tel_ds)) {
            List<XmkTels> telsByCompany = xmkTelsService.getTelsByCompany(tel_ds);
            //1）未查询到电话
            if (telsByCompany.size() <= 0) {
                List<XmkTels> telsByCity = xmkTelsService.getTels(diZhi);
                if (telsByCity.size() <= 0) {
                    map.put("res", "系统暂未录入" + tel_ds + "电话,请问还有什么能够帮助您？");
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    telsByCity.forEach(tels -> {
                        stringBuffer.append(tels.getCompany()).append(",,,,");
                    });
//                    map.put("res", "系统暂未录入" + tel_ds + "电话,为您查询到负责" + city + "招商工作的单位有：" + stringBuffer + "，是否需要将以上单位的联系方式发送到您的手机?");
                    map.put("res", "系统暂未录入" + tel_ds + "电话,为您查询到负责" + city + "招商工作的单位有：" + stringBuffer + "稍后会以短信的形式发送到您的手机,请问还有什么可以帮您?");
                    //存入发送短信缓存中
                    map.put("keyword", city);
                    ccNGDService.saveQ(phone,"3",city,session_id);
                }
                //2）已查询到电话
            } else {
                telsByCompany.forEach(tels -> {



                    map.put("res", tels.getCompany() + "值班室电话为：" + tels.getDutyRoom()+"稍后会以短信的形式发送到您的手机,请问还有什么能够帮您？");
                    //存入发送短信缓存中
                    map.put("keyword", city);
                });
                ccNGDService.saveQ(phone,"3",city,session_id);
            }
        }
        //2、未知地市厅局时按照地点查询电话
        if (StringUtils.isEmpty(tel_ds)) {
            List<XmkTels> tels = xmkTelsService.getTels(diZhi);
            // 1)查询到了电话
            if (tels.size() > 0) {
                StringBuffer stringBuffer = new StringBuffer();
                tels.forEach(tel -> {
                    stringBuffer.append(tel.getCompany() + "值班室电话为：" + tel.getDutyRoom() + ",,,,,");
                });
                map.put("res", stringBuffer.toString() + "稍后会以短信的形式发送到您的手机,请问还有什么可以帮您?");
                //存入发送短信缓存中
                map.put("keyword", city);
                // 2)未查询到电话
            } else {
                map.put("res", "您所咨询的地点暂无号码，还有什么可以帮您?");
            }
        }
        return map;
    }

    @Override
    public Map createScore(JSONObject context, String sessionId, String phone) {
//        phone = "17558739950";
        Map map = new HashMap();
        Double score = context.getDouble("gd_pf");
        if (!StringUtils.isEmpty(phone)) {
            CcScoreParam ccScoreParam = new CcScoreParam();
            int isPerson = 0;
            ccScoreParam.setPhone(phone);
            ccScoreParam.setIsPerson(String.valueOf(isPerson));
            ccScoreParam.setSessionId(sessionId);

            if (score > 1 && score < 2) {
                ccScoreParam.setScore("一分");
            } else if (score > 2 && score < 3) {
                ccScoreParam.setScore("二分");
            } else if (score > 3 && score < 4) {
                ccScoreParam.setScore("三分");
            } else if (score > 4 && score < 5) {
                ccScoreParam.setScore("四分");
            } else if (score == 1) {
                ccScoreParam.setScore("一分");
            } else if (score == 2) {
                ccScoreParam.setScore("二分");
            } else if (score == 3) {
                ccScoreParam.setScore("三分");
            } else if (score == 4) {
                ccScoreParam.setScore("四分");
            } else if (score == 5) {
                ccScoreParam.setScore("五分");
            } else {
                ccScoreParam.setScore("零分");
            }
            int res = ccSessionService.createScore(ccScoreParam);
            if(res< 0){
                System.out.println("插入评分失败！！！");
            }
        }
        map.put("res","谢谢，祝您生活愉快，再见");
        return map;
    }
}

