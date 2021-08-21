package com.example.cloudnativeweekjavaedition;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest
class ApplicationRestControllerTest {
    @Autowired
    WebTestClient webClient;

    @Test
    void canPerformRestCall() {
        System.out.println();
    }
}