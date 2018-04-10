package com.dragon.controller;


import org.springframework.web.bind.annotation.GetMapping;

public class PageController {

    @GetMapping("/")
    public String index() {

        return "index";
    }

}
