package com.sifu.sfcc.service;

import com.alibaba.excel.util.CollectionUtils;
import com.sifu.sfcc.common.exception.ApiException;
import com.sifu.sfcc.entity.ws.MsgPackage;
import com.sifu.sfcc.enums.ws.MsgTypeEnum;
import com.sifu.sfcc.util.MsgUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/cc/{userId}")
@Component
public class CCWebSocket {
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<String, Session>();
    private static AtomicInteger online = new AtomicInteger();

    public CCWebSocket() {
        System.out.println("CCWebSocket");
    }

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId) {
        sessionPools.put(userId, session);

    }

    /**
     * 关闭连接时调用
     *
     * @param userid userid
     */
    @OnClose
    public void onClose(@PathParam(value = "userid") String userid) {
        sessionPools.remove(userid);
        //subOnlineCount();
    }

    public void sendMessage(MsgPackage pkg)  {
        try{
            sendMessage(MsgUtils.gson.toJson(pkg));
        }catch(Exception e){
            e.printStackTrace();
            throw new ApiException("发送消息异常");
        }

    }

    /***
     * 核心业务发送方法
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        MsgPackage msgPkg = null;
        try {
            msgPkg = MsgUtils.gson.fromJson(message, MsgPackage.class);
        } catch (Exception e) {
            throw new ApiException("解析消息异常");
        }
        Integer chnnelType = msgPkg.getChnnelType();
        Optional<MsgTypeEnum> channelTypeEnum = Arrays.stream(MsgTypeEnum.values()).filter(e -> e.getType().equals(chnnelType)).findFirst();
        if (Optional.<MsgTypeEnum>empty() == channelTypeEnum || (channelTypeEnum.get() != MsgTypeEnum.TYPE_BROADCAST && channelTypeEnum.get() != MsgTypeEnum.TYPE_SINGLE)) {
            throw new ApiException("消息通道类型非法");
        }
        Integer msgType = msgPkg.getMsgType();
        Optional<MsgTypeEnum> msgTypeEnum = Arrays.stream(MsgTypeEnum.values()).filter(e -> e.getType().equals(msgType)).findFirst();
        if(Optional.<MsgTypeEnum>empty() == msgTypeEnum){
            throw new ApiException("业务类型非法");
        }

//        switch(msgTypeEnum.get()){
//            case TYPE_QRY_TODO://查询待办
//                break;
//            case TYPE_REMIND_TODO://催办
//                break;
//        }
        if(CollectionUtils.isEmpty(sessionPools)){
            return;
        }
        switch (channelTypeEnum.get()) {
            case TYPE_BROADCAST:
                for(Session sendSession :sessionPools.values()){
                    if(sendSession.isOpen()){
                        sendSession.getBasicRemote().sendText(message);
                    }

                }
                break;
            case TYPE_SINGLE:
                Session sendSession = null;
                for(String userId : sessionPools.keySet()){
                    if(userId.equals(msgPkg.getChannelTo())){
                        sendSession = sessionPools.get(userId);
                    }
                }
                if(!ObjectUtils.isEmpty(sendSession)){
                    if(sendSession.isOpen()){
                        sendSession.getBasicRemote().sendText(message);
                    }

                }
                break;
        }

    }

    /**
     * 收到客户端消息时触发（群发）
     *
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
        sendMessage(message);
    }

    /**
     * 发生错误时候
     *
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("发生错误");
        throwable.printStackTrace();
    }

    /**
     * 给指定用户发送消息
     *
     * @param userid  userid
     * @param message 消息
     * @throws IOException
     */
    public void sendInfo(String userid, String message) {
        Session session = sessionPools.get(userid);
        try {
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addOnlineCount() {
        online.incrementAndGet();
    }

    public static void subOnlineCount() {
        online.decrementAndGet();
    }

}
