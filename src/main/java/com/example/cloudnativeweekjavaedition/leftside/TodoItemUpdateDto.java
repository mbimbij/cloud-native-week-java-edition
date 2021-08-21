package com.example.cloudnativeweekjavaedition.leftside;

import com.example.cloudnativeweekjavaedition.core.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemUpdateDto {
    @NotNull(message = "id cannot be null when updating item")
    private Integer id;
    private String name;
    private State state;

    public TodoItemUpdateDto(String name, State state) {
        this.name = name;
        this.state = state;
    }
}
