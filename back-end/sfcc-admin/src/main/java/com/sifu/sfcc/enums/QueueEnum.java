package com.sifu.sfcc.enums;

import lombok.Getter;

/**
 * 消息队列枚举配置
 * Created by macro on 2018/9/14.
 */
@Getter
public enum QueueEnum {
    /**
     * 商品下架
     * 消息通知队列
     */
    QUEUE_GROUP_CLOSE("mall.group.direct", "mall.group.close", "mall.group.close"),
    /**
     * 商品下架
     * 消息通知ttl队列
     */
    QUEUE_TTL_GROUP_CLOSE("mall.group.direct.ttl", "mall.group.close.ttl", "mall.group.close.ttl");

    /**
     * 交换名称
     */
    private String exchange;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}