package com.sifu.sfcc.bo;

public class SendUrl {

    private String type;
    private String keyword;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "SendUrl{" +
                "type='" + type + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
