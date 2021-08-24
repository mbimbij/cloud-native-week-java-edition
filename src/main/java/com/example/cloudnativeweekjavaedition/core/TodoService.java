package com.example.cloudnativeweekjavaedition.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoItemDao dao;

    public Mono<TodoItem> create(TodoItem item) {
        return dao.create(item);
    }

    public Mono<Optional<TodoItem>> findById(int id) {
        return dao.findById(id);
    }

    public Flux<TodoItem> findAll() {
        return dao.findAll();
    }

    public Mono<TodoItem> update(TodoItem item) {
        return dao.update(item);
    }

    public Mono<Void> deleteById(int id) {
        return dao.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return dao.deleteAll();
    }
}
