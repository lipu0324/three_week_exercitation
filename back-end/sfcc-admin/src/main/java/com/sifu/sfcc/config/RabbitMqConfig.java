package com.sifu.sfcc.config;

import com.sifu.sfcc.enums.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置
 * Created by cc on 2018/9/14.
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 商品拼团时间实际消费队列所绑定的交换机
     */
    @Bean
    DirectExchange groupDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_GROUP_CLOSE.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 商品拼团时间延迟队列所绑定的交换机
     */
    @Bean
    DirectExchange groupTtlDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_TTL_GROUP_CLOSE.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 商品拼团时间实际消费队列
     */
    @Bean
    public Queue groupQueue() {
        return new Queue(QueueEnum.QUEUE_GROUP_CLOSE.getName());
    }

    /**
     * 商品拼团时间延迟队列（死信队列）
     */
    @Bean
    public Queue groupTtlQueue() {
        return QueueBuilder
                .durable(QueueEnum.QUEUE_TTL_GROUP_CLOSE.getName())
                .withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_GROUP_CLOSE.getExchange())//到期后转发的交换机
                .withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_GROUP_CLOSE.getRouteKey())//到期后转发的路由键
                .build();
    }

    /**
     * 将商品拼团时间队列绑定到交换机
     */
    @Bean
    Binding groupBinding(DirectExchange groupDirect, Queue groupQueue){
        return BindingBuilder
                .bind(groupQueue)
                .to(groupDirect)
                .with(QueueEnum.QUEUE_GROUP_CLOSE.getRouteKey());
    }

    /**
     * 将商品拼团时间延迟队列绑定到交换机
     */
    @Bean
    Binding groupTtlBinding(DirectExchange groupTtlDirect,Queue groupTtlQueue){
        return BindingBuilder
                .bind(groupTtlQueue)
                .to(groupTtlDirect)
                .with(QueueEnum.QUEUE_TTL_GROUP_CLOSE.getRouteKey());
    }

}