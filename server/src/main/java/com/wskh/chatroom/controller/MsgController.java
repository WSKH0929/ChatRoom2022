package com.wskh.chatroom.controller;

import com.wskh.chatroom.common.R;
import com.wskh.chatroom.communication.WebSocketServer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author：WSKH
 * @ClassName：MsgController
 * @ClassType：控制类
 * @Description：信息控制类
 * @Date：2022/1/25/12:47
 * @Email：1187560563@qq.com
 * @Blog：https://blog.csdn.net/weixin_51545953?type=blog
 */
@ApiModel("信息控制类")
@RestController
@RequestMapping("/chatroom/msg")
public class MsgController {
    @ApiOperation("发送信息方法")
    @PostMapping("/sendMsg")
    public R sendMsg(String msg) throws IOException {
        WebSocketServer.sendInfo(msg);
        return R.ok().message("发送成功");
    }
}
