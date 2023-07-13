package com.demo.controller;

import com.demo.pojo.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class helloSSM {

    @RequestMapping("/hello/{name}")
    public User hello(@PathVariable String name) {
        System.out.println(name);
        return new User("0001", name);
    }

}
