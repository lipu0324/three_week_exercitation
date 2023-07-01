package com.sifu.sfcc.dao;

import com.sifu.sfcc.bo.SaveSMS;
import com.sifu.sfcc.dto.ProjectParam;
import com.sifu.sfcc.model.XmkProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XmkKeywordDao {


    int saveQ(SaveSMS saveSMS);

    List<SaveSMS> selectKeyWordPhoneBySessionId(@Param("sessionId") String sessionId);

    List getSendInfo(@Param("type")String type);
}
