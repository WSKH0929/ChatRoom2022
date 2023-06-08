package com.wskh.chatroom.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wskh.chatroom.common.R;
import com.wskh.chatroom.entity.User;
import com.wskh.chatroom.entity.dto.LoginDto;
import com.wskh.chatroom.service.UserService;
import com.wskh.chatroom.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WSKH
 * @since 2022-01-25
 */
@RestController
@RequestMapping("/chatroom/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public R login(@RequestBody LoginDto loginDto, HttpServletResponse response){
        System.out.println(loginDto);
        User user = userService.getOne(new QueryWrapper<User>().eq("user_name", loginDto.getUsername()));
        System.out.println(user);
        //密码正确的时候生成jwt
        String jwt = jwtUtil.createToken(user.getId());
        //把jwt放到header中
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        return R.ok().data("token", user.getUserName()).data("avatar", "https://eduguli-13184.oss-cn-beijing.aliyuncs.com/wallhaven-43lg6d.jpg");
    }

    @GetMapping("/info")
    public R info(String token){
        User user = userService.getOne(new QueryWrapper<User>().eq("user_name", token));
        if(user==null){
            return R.ok();
        }
        return R.ok().data("roles", "[admin]").
                data("name", user.getUserName()).
                data("avatar", "https://eduguli-13184.oss-cn-beijing.aliyuncs.com/wallhaven-43lg6d.jpg");
    }

    @PostMapping("/logout")
    public R logout(){
        return R.ok().message("退出登录成功");
    }
}

