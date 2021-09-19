package com.example.cloudnativeweekjavaedition.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@Slf4j
public class TodoService {
    @Autowired
    private TodoItemDao dao;

    public Mono<TodoItem> create(TodoItem item) {
        log.info("creating item: {}", item.toString());
        return dao.create(item);
    }

    public Mono<Optional<TodoItem>> findById(int id) {
        log.info("looking for item: {}", id);
        return dao.findById(id);
    }

    public Flux<TodoItem> findAll() {
        log.info("looking for all items");
        return dao.findAll();
    }

    public Mono<TodoItem> update(TodoItem item) {
        log.info("updating item: {}", item.toString());
        return dao.update(item);
    }

    public Mono<Void> deleteById(int id) {
        log.info("deleting item: {}", id);
        return dao.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        log.info("deleting all items");
        return dao.deleteAll();
    }
}
