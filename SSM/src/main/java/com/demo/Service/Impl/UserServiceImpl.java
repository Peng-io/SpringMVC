package com.demo.Service.Impl;

import com.demo.Service.UserService;
import com.demo.mapper.UserMapper;
import com.demo.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    

    @Override
    public List<User> selectUser() {
        return userMapper.selectUser();
    }
}
