package com.example.cloudnativeweekjavaedition.leftside;

import com.example.cloudnativeweekjavaedition.core.TodoItem;
import com.example.cloudnativeweekjavaedition.core.TodoItemDao;
import com.example.cloudnativeweekjavaedition.core.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.time.Duration;

@RestController
@RequestMapping("/items")
public class ApplicationRestController {

    @Autowired
    private TodoService service;

    @PostMapping
    public Mono<ResponseEntity<TodoItemDto>> createItem(@RequestBody TodoItemDto newItem) {
        TodoItem todoItem = new TodoItem(newItem.getName(), newItem.getState());
        return service.create(todoItem)
                .map(item -> new TodoItemDto(item.getId(), item.getName(), item.getState()))
                .map(todoItemDto -> ResponseEntity.status(HttpStatus.CREATED)
                        .header("Location", "/items/" + todoItemDto.getId())
                        .body(todoItemDto));
    }

    @GetMapping(produces = MediaType.APPLICATION_NDJSON_VALUE)
    public ResponseEntity<Flux<TodoItemDto>> getAllItems() {
        return ResponseEntity.ok()
                .body(service.findAll()
                        .doOnEach(System.out::println)
                        .delayElements(Duration.ofSeconds(1))
                        .map(item -> new TodoItemDto(item.getId(), item.getName(), item.getState())));
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<TodoItemDto>> getItemById(@PathVariable("id") int id) {
        return service.findById(id)
                .map(optionalEntity -> optionalEntity
                        .map(todoItem -> new TodoItemDto(todoItem.getId(), todoItem.getName(), todoItem.getState())))
                .map(optionalDto -> optionalDto
                        .map(todoItemDto -> ResponseEntity.ok().body(todoItemDto))
                        .orElse(ResponseEntity.notFound().build()));

    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<TodoItemUpdateDto>> updateItem(@Valid @RequestBody TodoItemUpdateDto newItem, @PathVariable("id") int id) {
        TodoItem todoItemUpdated = new TodoItem(newItem.getId(), newItem.getName(), newItem.getState());
        return service.findById(id)
                .flatMap(optionalEntity -> optionalEntity
                        .map(item -> service.update(todoItemUpdated)
                                .map(updatedItem -> new TodoItemUpdateDto(updatedItem.getId(), updatedItem.getName(), updatedItem.getState()))
                                .map(todoItemDto -> ResponseEntity.ok().body(todoItemDto)))
                        .orElse(service.update(todoItemUpdated)
                                .map(createdItem -> new TodoItemUpdateDto(createdItem.getId(), createdItem.getName(), createdItem.getState()))
                                .map(todoItemDto -> ResponseEntity.created(URI.create("items/" + todoItemDto.getId())).body(todoItemDto))));
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<TodoItemDto>> deleteItemById(@PathVariable("id") int id) {
        return service.deleteById(id)
                .map(unused -> ResponseEntity.noContent().build());
    }

    @DeleteMapping
    public Mono<ResponseEntity<TodoItemDto>> deleteAll() {
        return service.deleteAll()
                .map(unused -> ResponseEntity.noContent().build());
    }
}
