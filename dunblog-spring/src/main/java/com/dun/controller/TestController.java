package com.dun.controller;

import com.dun.common.lang.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {


        return "hello world";
    }

    @GetMapping("/test")
    public Result Test(){

        return Result.succ("测试！！");
    }
}
