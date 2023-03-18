package com.mdas.api.g6.user.infrastructure.controller;

import com.mdas.api.g6.shared.infrastructure.controller.ApiResponse;
import com.mdas.api.g6.user.application.CreateUserUseCase;
import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody String name) {
        User user;
        try {
            user = createUserUseCase.execute(name);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(HttpStatus.CONFLICT, e.getMessage(), null));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED, null, user));
    }
}
