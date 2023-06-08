package com.wskh.chatroom.communication;

import com.wskh.chatroom.util.FastJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.EOFException;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {

    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    private static int onlineCount = 0;

    private static ConcurrentHashMap<String,WebSocketServer> webSocketServerMap = new ConcurrentHashMap<>();

    private Session session;

    private String sid;


    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.sid = sid;
        this.session = session;
        webSocketServerMap.put(sid, this);
        addOnlineCount();
        log.info("有新窗口开始监听:"+sid+",当前在线人数为" + getOnlineCount());
        try {
            sendInfo("openSuccess:"+webSocketServerMap.keySet());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose() {
        webSocketServerMap.remove(sid);
        subOnlineCount();
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
        try {
            sendInfo("openSuccess:"+webSocketServerMap.keySet());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        if("ping".equals(message)) {
            sendInfo(sid, "pong");
        }
        if(message.contains(":")) {
            String[] split = message.split(":");
            sendInfo(split[0], "receivedMessage:"+sid+":"+split[1]);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        if(error instanceof EOFException) {
            return;
        }
        if(error instanceof IOException && error.getMessage().contains("已建立的连接")) {
            return;
        }
        log.error("发生错误", error);
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        synchronized (session) {
            this.session.getBasicRemote().sendText(message);
        }
    }

    public static void sendObject(Object obj) throws IOException {
        sendInfo(FastJsonUtils.convertObjectToJSON(obj));
    }

    public static void sendInfo(String sid,String message) throws IOException {
        WebSocketServer socketServer = webSocketServerMap.get(sid);
        if(socketServer != null) {
            socketServer.sendMessage(message);
        }
    }

    public static void sendInfo(String message) throws IOException {
        for(String sid : webSocketServerMap.keySet()) {
            webSocketServerMap.get(sid).sendMessage(message);
        }
    }

    public static void sendInfoByUserId(Long userId,Object message) throws IOException {
        for(String sid : webSocketServerMap.keySet()) {
            String[] sids =  sid.split("id");
            if(sids.length == 2) {
                String id = sids[1];
                if(userId.equals(Long.parseLong(id))) {
                    webSocketServerMap.get(sid).sendMessage(FastJsonUtils.convertObjectToJSON(message));
                }
            }
        }
    }

    public static Session getWebSocketSession(String sid) {
        if(webSocketServerMap.containsKey(sid)) {
            return webSocketServerMap.get(sid).session;
        }
        return null;
    }

    public static synchronized void addOnlineCount() {
        onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        onlineCount--;
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
}

