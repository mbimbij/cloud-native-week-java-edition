package com.example.cloudnativeweekjavaedition.rightside.h2;

import com.example.cloudnativeweekjavaedition.core.State;
import com.example.cloudnativeweekjavaedition.core.TodoItem;
import com.example.cloudnativeweekjavaedition.core.TodoItemDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@EnabledIf(expression = "#{environment.acceptsProfiles('local')}", loadContext = true)
@ActiveProfiles("h2")
class TodoItemDaoH2IT {

    @Autowired
    @Qualifier("todoItemDaoH2")
    private TodoItemDao todoItemDao;

    @BeforeEach
    void setUp() {
        todoItemDao.deleteAll().block();
    }

    @Test
    void canSave_andFindAllEntities() {
        assertThat(todoItemDao.findAll().toStream().collect(Collectors.toList())).isEmpty();

        // WHEN
        todoItemDao.create(new TodoItem("someName", State.TODO)).block();
        assertThat(todoItemDao.findAll().toStream().collect(Collectors.toList())).hasSize(1);
    }

    @Test
    void canSave_andFindEntityById() {
        TodoItem item1 = todoItemDao.create(new TodoItem("item 1", State.TODO)).block();
        todoItemDao.create(new TodoItem("item 2", State.DOING)).block();
        TodoItem expectedItem1FromDao = new TodoItem("item 1", State.TODO);

        // WHEN
        Optional<TodoItem> item1FromDaoOptional = todoItemDao.findById(item1.getId()).block();

        // THEN
        assertThat(item1FromDaoOptional.get())
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedItem1FromDao);
    }

    @Test
    void canSave_andDeleteEntities() {
        TodoItem item1 = todoItemDao.create(new TodoItem("item 1", State.TODO)).block();
        TodoItem item2 = todoItemDao.create(new TodoItem("item 2", State.DOING)).block();
        assertThat(todoItemDao.findAll().toStream().collect(Collectors.toList())).hasSize(2);

        // WHEN
        todoItemDao.deleteById(item1.getId()).block();

        // THEN
        assertThat(todoItemDao.findAll().toStream().collect(Collectors.toList())).hasSize(1);

        // WHEN
        todoItemDao.deleteAll().block();

        // THEN
        assertThat(todoItemDao.findAll().toStream().collect(Collectors.toList())).isEmpty();
    }
}