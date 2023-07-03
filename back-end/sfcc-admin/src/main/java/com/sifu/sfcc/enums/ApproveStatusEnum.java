package com.sifu.sfcc.enums;

public enum ApproveStatusEnum {
    TO_RECIEVE(0,"待接收"),
    RECIEVED(1,"接收"),
    REJECT(2,"拒收"),
    PASSED(3,"结单/已处理"),
    MOVED(4,"转办"),
    WITHDRAW(5,"撤回"),
    REMID(6,"催办"),
    CANCEl(7,"作废"),
    ;
    ApproveStatusEnum(int code, String name){
        this.code = code;
        this.name = name;
    }
    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
