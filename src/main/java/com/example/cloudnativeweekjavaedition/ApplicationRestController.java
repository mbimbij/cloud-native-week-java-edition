package com.example.cloudnativeweekjavaedition;

import org.springframework.web.bind.annotation.GetMapping;

public class ApplicationRestController {
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
