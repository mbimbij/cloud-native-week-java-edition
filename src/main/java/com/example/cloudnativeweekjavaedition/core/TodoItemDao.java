package com.example.cloudnativeweekjavaedition.core;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface TodoItemDao {
    Mono<TodoItem> create(TodoItem item);

    Mono<TodoItem> update(TodoItem item);

    Mono<Optional<TodoItem>> findById(int id);

    Flux<TodoItem> findAll();

    Mono<Void> deleteById(int id);

    Mono<Void> deleteAll();
}
