package com.demo.controller;

import com.demo.Service.Impl.UserServiceImpl;
import com.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class helloSSM {
    @Autowired
    UserServiceImpl userService;


    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/user")
    public List<User> user() {
        return userService.selectUser();
    }
}
