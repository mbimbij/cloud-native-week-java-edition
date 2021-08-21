package com.example.cloudnativeweekjavaedition;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationRestController {
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
