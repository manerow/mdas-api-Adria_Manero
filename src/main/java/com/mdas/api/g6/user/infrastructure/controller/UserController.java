package com.mdas.api.g6.user.infrastructure.controller;

import com.mdas.api.g6.shared.infrastructure.controller.ApiResponse;
import com.mdas.api.g6.user.application.AddPokemonFavoriteUseCase;
import com.mdas.api.g6.user.application.CreateUser;
import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.exception.PokemonAlreadyAddException;
import com.mdas.api.g6.user.domain.exception.UserAlreadyExistsException;
import com.mdas.api.g6.user.domain.exception.UserNotFoundException;
import com.mdas.api.g6.user.infrastructure.controller.dto.AddFavoritePokemonRequest;
import com.mdas.api.g6.user.infrastructure.controller.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final CreateUser createUserUseCase;
    private final AddPokemonFavoriteUseCase addPokemonFavoriteUseCase;

    @PostMapping(value = "/create")
    public ResponseEntity<ApiResponse<User>> create(@RequestBody CreateUserRequest createUserRequest) {
        User user;
        try {
            user = createUserUseCase.execute(createUserRequest.getName());
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(HttpStatus.CONFLICT, e.getMessage(), null));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED, null, user));
    }

    @PostMapping("/add-favorite-pokemon")
    public ResponseEntity<ApiResponse<User>> addFavoritePokemon(
            @RequestHeader("user_id") String userId,
            @RequestBody AddFavoritePokemonRequest request) {

        User result;

        try {
            result = addPokemonFavoriteUseCase.execute(userId, request);
        } catch (PokemonAlreadyAddException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(HttpStatus.CONFLICT, e.getMessage(), null));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED, null, result));
    }

}
