package com.sifu.sfcc.client;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

/**
 * @author zhouyunfei
 * @date 2022/12/14 11:14
 */
//暂时关闭
//@Configuration
@Slf4j
public class WebSocketLoginConfig {
    /**
     * 后端模拟websocket客户端
     *
     * @return MyWebSocketClient
     */
    @Bean
    public static MyWebSocketClient myWebSocketClient() {
        String wsUrl = "ws://192.168.38.11:8097/websocket";
        URI uri = URI.create(wsUrl);
        return new MyWebSocketClient(uri);
    }

    public static void main(String[] args) {
        //myWebSocketClient();
        try {
            String url = "ws://111.40.37.223:8097/websocket";
            URI uri = new URI(url);
            WebSocketClient mWs = new WebSocketClient(uri){
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    heartCheck();
                    log.info("与云服务器成功建立WS连接");
                }

                @Override
                public void onMessage(String s) {
                    heartCheck();
                    log.info("-------- 接收到服务端数据： " + s + "--------");
                    send("发送一个心跳成功");
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
                        send("....发送一个心跳.....");
                    }
                }
            };
            mWs.connect();
            while (!mWs.getReadyState().equals(ReadyState.OPEN)) {
                log.info("连接中...");
            }
            JSONObject json = new JSONObject();
            json.put("thisDN", "10000_5007");
            json.put("type","login");
            JSONObject message = new JSONObject();
            message.put("messageId", 100);
            message.put("thisDN", "10000_5007");
            message.put("extension", "1004");
            message.put("thisQueues", "10000_8002");
            message.put("attachDatas", "{\"typeCode\":\"20\"}");
            json.put("message", message);
            log.info("入参" + json);
            mWs.send(json.toString());
            System.out.println("haha");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
