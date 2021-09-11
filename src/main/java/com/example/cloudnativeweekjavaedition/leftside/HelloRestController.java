package com.example.cloudnativeweekjavaedition.leftside;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/hello")
public class HelloRestController {
    private String id = UUID.randomUUID().toString();

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<String> hello() {
        return Mono.just("hello "+id);
    }
}
