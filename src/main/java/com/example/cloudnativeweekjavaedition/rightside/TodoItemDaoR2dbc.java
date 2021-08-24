package com.example.cloudnativeweekjavaedition.rightside;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TodoItemDaoR2dbc extends ReactiveCrudRepository<TodoItemEntity, Integer> {
}
