package com.sifu.sfcc.client;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;

import javax.annotation.PostConstruct;
import java.net.URI;

/**
 * @author zhouyunfei
 * @date 2022/12/14 11:11
 */
@Slf4j
public class MyWebSocketClient extends WebSocketClient {
    public MyWebSocketClient(URI uri) {
        super(uri);
    }

    @PostConstruct
    private void init() {
        try {
            log.info("进入初始化方法");
            this.connectBlocking();
            //开启心跳重连线程
            new Thread(() -> {
                while (true) {
                    try {
                        //当连接状态不为open时每5秒重连一次
                        if (getReadyState() != ReadyState.OPEN) {
                            log.info("发起重新连接请求");
                            reconnectBlocking();
                            Thread.sleep(5 * 1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        heartCheck();
        log.info("与云服务器成功建立WS连接");
        if (getReadyState() == ReadyState.OPEN) {
            log.info("....发送一个心跳.....");
            JSONObject json = new JSONObject();
            json.put("thisDN", "10000_5007");
            json.put("type","login");
            JSONObject message = new JSONObject();
            message.put("messageId", 100);
            message.put("thisDN", "10000_5007");
            message.put("extension", "1004");
            message.put("thisQueues", "[\"10000_8002\"]");
            message.put("attachDatas", "{\"typeCode\":\"20\"}");
            json.put("message", message);
            log.info("入参" + json);
            send(json.toString());
        }
    }

    @Override
    public void onMessage(String s) {
        heartCheck();
        log.info("-------- 接收到服务端数据： " + s + "--------");
        //send("....发送一个心跳.....");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.warn("readyState:" + getReadyState() + "," + code + "-reason:" + reason + ",与云服务器断开了连接,尝试重连...");
    }

    @Override
    public void onError(Exception e) {
        log.error("webSocket发生异常: {}:{}", e.getClass().getName(), e.getMessage());
        close();
    }

    public void heartCheck() {
        if (getReadyState() == ReadyState.OPEN) {
            log.info("....发送一个心跳.....");
        }
    }
}
