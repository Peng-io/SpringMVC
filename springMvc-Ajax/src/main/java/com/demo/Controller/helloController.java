package com.demo.Controller;


import com.alibaba.fastjson2.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class helloController {

    @RequestMapping(value = "/hello", produces = "application/json;charset=utf-8")
    public String hello() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "李四");
        String s = JSON.toJSONString(map);
        System.out.println(s);
        return s;
    }

    @RequestMapping(value = "/getAll", produces = "application/json;charset=utf-8")
    public List<student> hello2() {
        List<student> list = new ArrayList<>();
        list.add(new student("001", "李四"));
        list.add(new student("002", "王五"));
        return list;
    }
}

class student {
    String id;
    String name;

    public student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}