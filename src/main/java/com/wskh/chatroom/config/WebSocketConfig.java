package com.wskh.chatroom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author：WSKH
 * @ClassName：WebSocketConfig
 * @ClassType：配置类
 * @Description：WebSocket配置类
 * @Date：2022/1/25/12:21
 * @Email：1187560563@qq.com
 * @Blog：https://blog.csdn.net/weixin_51545953?type=blog
 */
@Configuration
public class WebSocketConfig {
    /**
     * 开启webSocket
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
