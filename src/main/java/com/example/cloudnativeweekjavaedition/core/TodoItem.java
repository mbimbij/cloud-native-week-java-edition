package com.example.cloudnativeweekjavaedition.core;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoItem {
    private Integer id;
    private String name;
    private State state;

    public TodoItem(String name, State state) {
        this.name = name;
        this.state = state;
    }
}
