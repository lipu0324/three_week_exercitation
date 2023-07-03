package com.sifu.sfcc.enums.ws;

public enum MsgTypeEnum {
    TYPE_BROADCAST(1001,"msg_b"),//广播 消息
    TYPE_SINGLE(1002,"msg_s"),//单条 普通消息

    TYPE_QRY_TODO(1003,"msg_q_t"),//查询待办
    TYPE_REMIND_TODO(1004,"msg_r_t"),//催办待办
//    TYPE_MOVE_TODO(1005,"msg_m_t"),//转办消息
//    TYPE_REJECT_TODO(1006,"msg_r_t"),//拒收

    ;
    private Integer type;
    private String typeName;
    MsgTypeEnum(Integer type ,String typeName){
        this.type = type;
        this.typeName = typeName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
