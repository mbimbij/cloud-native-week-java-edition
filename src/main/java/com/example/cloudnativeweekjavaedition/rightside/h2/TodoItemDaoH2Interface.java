package com.example.cloudnativeweekjavaedition.rightside.h2;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TodoItemDaoH2Interface extends ReactiveCrudRepository<TodoItemEntity, Integer> {
}
