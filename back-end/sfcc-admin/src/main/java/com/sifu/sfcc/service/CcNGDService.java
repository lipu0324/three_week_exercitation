package com.sifu.sfcc.service;

import java.util.List;

public interface CcNGDService {
    String dealMobile(String ngdStr, Integer count);
    
    String sendSms(String mobile,String tpye, String problem);

    int saveQ(String mobile, String type, String keyword, String campaign_id);

    String NGDSendSms(String sessionId);

    List getSendInfo(String type);
}
