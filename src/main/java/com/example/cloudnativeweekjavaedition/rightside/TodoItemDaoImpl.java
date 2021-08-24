package com.example.cloudnativeweekjavaedition.rightside;

import com.example.cloudnativeweekjavaedition.core.State;
import com.example.cloudnativeweekjavaedition.core.TodoItem;
import com.example.cloudnativeweekjavaedition.core.TodoItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public class TodoItemDaoImpl implements TodoItemDao {

    @Autowired
    private TodoItemDaoR2dbc h2Interface;

    @Override
    public Mono<TodoItem> create(TodoItem item) {
        TodoItemEntity itemEntity = new TodoItemEntity(item.getName(), item.getState().toString());
        return h2Interface.save(itemEntity).map(entity -> new TodoItem(entity.getId(), entity.getName(),
                State.valueOf(entity.getState())));
    }

    @Override
    public Mono<TodoItem> update(TodoItem item) {
        TodoItemEntity itemEntity = new TodoItemEntity(item.getId(), item.getName(), item.getState().toString());
        return h2Interface.save(itemEntity).map(entity -> new TodoItem(entity.getId(),
                entity.getName(),
                State.valueOf(entity.getState())));
    }

    @Override
    public Mono<Optional<TodoItem>> findById(int id) {
        return h2Interface.findById(id)
                .map(Optional::ofNullable)
                .map(optionalEntity -> optionalEntity
                        .map(itemEntity -> new TodoItem(itemEntity.getId(),
                                itemEntity.getName(),
                                State.valueOf(itemEntity.getState()))));
    }

    @Override
    public Flux<TodoItem> findAll() {
        return h2Interface.findAll()
                .map(entity -> new TodoItem(entity.getId(),
                        entity.getName(),
                        State.valueOf(entity.getState())));
    }

    @Override
    public Mono<Void> deleteById(int id) {
        return h2Interface.deleteById(id);
    }

    @Override
    public Mono<Void> deleteAll() {
        return h2Interface.deleteAll();
    }
}
