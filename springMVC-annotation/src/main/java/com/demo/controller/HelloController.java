package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    //真实访问地址 : 项目名/hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(Model model) {
        //向模型中添加属性msg与值，可以在JSP页面中取出并渲染
        model.addAttribute("msg", "注解实现");
        //web-inf/jsp/hello.jsp
        return "hello";
    }

    @RequestMapping(value = "/index", params = {"id=1", "name=地瓜"})
    //写死的路径 http://localhost:8080/index?id=1&name=digua 不可变
    public String index(Integer id, String name, Model model) {
        System.out.println("id：" + id + " " + "name：" + name);
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        return "index";
    }

    @RequestMapping("/index2")
    // 获取路径参数 http://localhost:8080/index2?num=1&name=digua 可变
    public String index2(@RequestParam("num") Integer num, @RequestParam("name") String name, Model model) {
        System.out.println("num：" + num + " " + "name：" + name);
        model.addAttribute("num", num);
        model.addAttribute("name", name);
        return "index2";
    }

    @RequestMapping("/restful/{num}/{name}")
    // restful风格的URL参数获取 http://localhost:8080/restful/1/digua
    public String restful(@PathVariable("num") Integer num, @PathVariable("name") String name, Model model) {
        System.out.println("num：" + num + " " + "name：" + name);
        model.addAttribute("num", num);
        model.addAttribute("name", name);
        return "restful";
    }

    // 重定向
    @RequestMapping("/redirect")
    public String redirect() {
        System.out.println("重定向");
        // 指向 web-inf 下的 jsp文件
        return "redirect:/index.jsp";
    }
}
