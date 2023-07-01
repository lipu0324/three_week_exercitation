package com.sifu.sfcc.enums;

/***
 * 工单状态
 */
public enum WorderStatusEnum {

    /**
     *初始化
     */
    INIT(0),
    /***
     * 处理中
     */
    PENDING(1),
    /**
     * 已完成
     */
    FINISH(2),
    /**
     * 作废
     */
    INVALID(3),
    /**
     * 已完成
     */
    TIME_OUT(4),
    ;
    WorderStatusEnum(Integer code){
        this.code = code;

    }

    private Integer code;


    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
