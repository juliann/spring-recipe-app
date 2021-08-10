package com.nadarzy.springrecipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/", "", "/index.html", "index"})
    public String getIndexPage(){
        System.out.println("im here");
        System.out.println("hello");
        System.out.println("hello");
        System.out.println("hello");
        return "index";
    }
}
