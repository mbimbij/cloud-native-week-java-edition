package com.example.cloudnativeweekjavaedition.rightside.h2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("TODO_ITEMS")
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemEntity {
    @Id
    private int id;
    private String name;
    private String state;

    public TodoItemEntity(String name, String state) {
        this.name = name;
        this.state = state;
    }
}
