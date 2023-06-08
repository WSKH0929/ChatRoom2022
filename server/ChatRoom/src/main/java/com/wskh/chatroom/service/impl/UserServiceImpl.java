package com.wskh.chatroom.service.impl;

import com.wskh.chatroom.entity.User;
import com.wskh.chatroom.mapper.UserMapper;
import com.wskh.chatroom.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WSKH
 * @since 2022-01-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
