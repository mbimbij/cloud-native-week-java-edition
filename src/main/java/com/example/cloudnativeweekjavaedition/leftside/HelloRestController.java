package com.example.cloudnativeweekjavaedition.leftside;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Profile({"staging", "prod"})
@RequestMapping("/hello")
public class HelloRestController {
    private String id;

    public HelloRestController() {
        id = new RestTemplate().getForObject("http://169.254.169.254/latest/meta-data/instance-id", String.class);
    }

    @GetMapping
    public String hello() {
        new RestTemplate();
        return "hello 2 " + id;
    }
}
