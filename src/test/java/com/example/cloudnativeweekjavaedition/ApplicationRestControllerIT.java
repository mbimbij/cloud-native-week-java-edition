package com.example.cloudnativeweekjavaedition;

import com.example.cloudnativeweekjavaedition.core.State;
import com.example.cloudnativeweekjavaedition.core.TodoItemDao;
import com.example.cloudnativeweekjavaedition.leftside.TodoItemDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationRestControllerIT {
    private WebClient webClient;

    @Autowired
    private TodoItemDao todoItemDao;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        todoItemDao.deleteAll().block();
        webClient = WebClient.builder()
                .baseUrl(String.format("http://localhost:%d", port))
                .build();
    }

    @Test
    void canCreateItem() {
        TodoItemDto item = new TodoItemDto("item", State.TODO);
        ResponseEntity<TodoItemDto> response = webClient.post()
                .uri("/items")
                .body(Mono.just(item), TodoItemDto.class)
                .retrieve()
                .toEntity(TodoItemDto.class)
                .block(Duration.ofSeconds(2));

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            softAssertions.assertThat(response.getHeaders().getLocation()).asString().matches("/items/[0-9]*");
            softAssertions.assertThat(response.getBody())
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(item);
        });
    }

    @Test
    void canRetrieveItems() {
        TodoItemDto item1 = new TodoItemDto("item1", State.TODO);
        TodoItemDto item2 = new TodoItemDto("item2", State.DOING);

        // WHEN
        ResponseEntity<TodoItemDto> responseEntity1 = webClient.post()
                .uri("/items")
                .body(Mono.just(item1), TodoItemDto.class)
                .retrieve()
                .toEntity(TodoItemDto.class)
                .block();
        ResponseEntity<TodoItemDto> responseEntity2 = webClient.post()
                .uri("/items")
                .body(Mono.just(item2), TodoItemDto.class)
                .retrieve()
                .toEntity(TodoItemDto.class)
                .block();

        // THEN
        int locationPathId1 = Integer.parseInt(responseEntity1.getHeaders().getLocation().getPath().split("/")[2]);
        int locationPathId2 = Integer.parseInt(responseEntity2.getHeaders().getLocation().getPath().split("/")[2]);
        assertThat(locationPathId2).isEqualTo(locationPathId1 + 1);
    }

    @Test
    void canUpdateItem() {
        TodoItemDto item = new TodoItemDto("item1", State.TODO);
        TodoItemDto expecteditem = new TodoItemDto("item1", State.DONE);

        // WHEN
        TodoItemDto itemAfterCreation = webClient.post()
                .uri("/items")
                .body(Mono.just(item), TodoItemDto.class)
                .retrieve()
                .toEntity(TodoItemDto.class)
                .block()
                .getBody();
        itemAfterCreation.setState(State.DONE);
        ResponseEntity<TodoItemDto> block = webClient.put()
                .uri("/items/" + itemAfterCreation.getId())
                .body(Mono.just(itemAfterCreation), TodoItemDto.class)
                .retrieve()
                .toEntity(TodoItemDto.class)
                .block();
        ResponseEntity<TodoItemDto> itemAfterUpdateEntity = webClient.get()
                .uri("/items/" + itemAfterCreation.getId())
                .retrieve()
                .toEntity(TodoItemDto.class)
                .block();

        // THEN
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(itemAfterUpdateEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
            softAssertions.assertThat(itemAfterUpdateEntity.getBody())
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(expecteditem);
        });
    }

    @Test
    void canDeleteItems() {
        // GIVEN
        TodoItemDto item1 = new TodoItemDto("item1", State.TODO);
        TodoItemDto item2 = new TodoItemDto("item2", State.DOING);
        TodoItemDto item3 = new TodoItemDto("item3", State.DONE);

        ResponseEntity<TodoItemDto> responseEntity1 = webClient.post()
                .uri("/items")
                .body(Mono.just(item1), TodoItemDto.class)
                .retrieve()
                .toEntity(TodoItemDto.class)
                .block();
        ResponseEntity<TodoItemDto> responseEntity2 = webClient.post()
                .uri("/items")
                .body(Mono.just(item2), TodoItemDto.class)
                .retrieve()
                .toEntity(TodoItemDto.class)
                .block();
        ResponseEntity<TodoItemDto> responseEntity23 = webClient.post()
                .uri("/items")
                .body(Mono.just(item3), TodoItemDto.class)
                .retrieve()
                .toEntity(TodoItemDto.class)
                .block();
        ResponseEntity<List<TodoItemDto>> findAllresponseEntity1 = webClient.get()
                .uri("items/")
                .retrieve()
                .toEntityList(TodoItemDto.class)
                .block();
        assertThat(findAllresponseEntity1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(findAllresponseEntity1.getBody()).hasSize(3);
        Integer idItem1 = responseEntity1.getBody().getId();

        // WHEN
        ResponseEntity<Object> block = webClient.delete()
                .uri("items/" + idItem1)
                .retrieve()
                .toEntity(Object.class)
                .block();

        // THEN
        assertThat(webClient.get()
                .uri("items/")
                .retrieve()
                .toEntityList(TodoItemDto.class)
                .block().getBody()).hasSize(2);
    }
}