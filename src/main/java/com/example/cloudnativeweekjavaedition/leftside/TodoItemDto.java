package com.example.cloudnativeweekjavaedition.leftside;

import com.example.cloudnativeweekjavaedition.core.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemDto {
    private Integer id;
    private String name;
    private State state;

    public TodoItemDto(String name, State state) {
        this.name = name;
        this.state = state;
    }
}
