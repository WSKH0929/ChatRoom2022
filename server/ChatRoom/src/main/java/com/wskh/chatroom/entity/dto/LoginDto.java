package com.wskh.chatroom.entity.dto;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * @Author：WSKH
 * @ClassName：LoginDto
 * @ClassType：数据传输类
 * @Description：登录对象
 * @Date：2022/1/25/10:40
 * @Email：1187560563@qq.com
 * @Blog：https://blog.csdn.net/weixin_51545953?type=blog
 */
@Data
@ToString
public class LoginDto {
    String username;
    String password;
}
