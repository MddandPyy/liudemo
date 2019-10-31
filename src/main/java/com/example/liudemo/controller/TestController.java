package com.example.liudemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lzp
 * @version V1.0
 * @Classname TestController
 * @Date 2019/10/31 8:43
 */
@Controller
public class TestController {
    @ResponseBody
    @RequestMapping("/hello")
    public String helloWorld(){
        return "liu,hello";
    }

}
