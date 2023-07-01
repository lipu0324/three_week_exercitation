package com.sifu.sfcc.bo;

import org.joda.time.DateTime;

public class SaveSMS {
    private Long id;

    private String keyword;

    private String type;

    private String phone;

    private String callId;

    private DateTime createTime;


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public DateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SaveSMS{" +
                "id=" + id +
                ", keyword='" + keyword + '\'' +
                ", type='" + type + '\'' +
                ", phone='" + phone + '\'' +
                ", callId='" + callId + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
