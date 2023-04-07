package com.mdas.api.g6.integration;

import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.objectmother.PokemonIdMother;
import com.mdas.api.g6.shared.infrastructure.controller.dto.ApiResponse;
import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.infrastructure.controller.dto.AddFavoritePokemonRequest;
import com.mdas.api.g6.user.user.infrastructure.controller.dto.CreateUserRequest;
import com.mdas.api.g6.user.user.objectmother.UserIdMother;
import com.mdas.api.g6.user.user.objectmother.UserNameMother;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
public class UserAddFavoritePokemonIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private CreateUserRequest createUserRequest;
    private AddFavoritePokemonRequest addFavoritePokemonRequest;

    @Container
    private static final RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3-alpine");

    @DynamicPropertySource
     static void registerRabbitMQProperties(DynamicPropertyRegistry registry) {
        // Set RabbitMQ properties for the test
        registry.add("spring.rabbitmq.host", rabbitMQContainer::getHost);
        registry.add("spring.rabbitmq.port", rabbitMQContainer::getAmqpPort);
        registry.add("spring.rabbitmq.username", rabbitMQContainer::getAdminUsername);
        registry.add("spring.rabbitmq.password", rabbitMQContainer::getAdminPassword);
    }

    @BeforeEach
    void setUp() {
        addFavoritePokemonRequest = new AddFavoritePokemonRequest(PokemonIdMother.random().getId());
        createUserRequest = new CreateUserRequest(
                UserIdMother.random().getId(), UserNameMother.random().getName());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateUserRequest> request = new HttpEntity<>(createUserRequest, headers);

        ResponseEntity<ApiResponse<User>> response = restTemplate.exchange(
                "/user/create",
                HttpMethod.POST,
                request,
                ParameterizedTypeReference.forType(TypeUtils.parameterize(ApiResponse.class, User.class))
        );
    }

    @Test
    void testUserAddFavoritePokemon() {
        // GIVEN
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("user_id", createUserRequest.getId().toString());
        HttpEntity<AddFavoritePokemonRequest> request = new HttpEntity<>(addFavoritePokemonRequest, headers);

        // WHEN
        ResponseEntity<ApiResponse<User>> response = restTemplate.exchange(
                "/user/add-favorite-pokemon",
                HttpMethod.POST,
                request,
                ParameterizedTypeReference.forType(TypeUtils.parameterize(ApiResponse.class, User.class))
        );


        ResponseEntity<ApiResponse<Pokemon>> response1 = restTemplate.exchange("/pokemon/detail?id=" + addFavoritePokemonRequest.getPokemonId(),
                HttpMethod.GET, null, ParameterizedTypeReference.forType(TypeUtils.parameterize(ApiResponse.class, Pokemon.class)));

        // THEN
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNull(response.getBody().getMessage());
        assertNotNull(response.getBody().getData());
        assertNotEquals(0, response.getBody().getData().getFavoritePokemons().getFavoritePokemons().size());

        // Then
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertNotNull(response1.getBody().getData());
        assertEquals(1, response1.getBody().getData().getFavorites().getFavorites());
    }


}
