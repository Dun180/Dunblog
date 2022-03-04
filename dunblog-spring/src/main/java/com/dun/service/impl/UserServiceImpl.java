package com.dun.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dun.entity.User;
import com.dun.mapper.UserMapper;
import com.dun.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
