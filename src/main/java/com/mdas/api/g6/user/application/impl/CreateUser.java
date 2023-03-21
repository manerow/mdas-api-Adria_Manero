package com.mdas.api.g6.user.application.impl;

import com.mdas.api.g6.user.application.CreateUserUseCase;
import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.exception.UserAlreadyExistsException;
import com.mdas.api.g6.user.domain.services.UserCreator;
import com.mdas.api.g6.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.domain.valueobject.UserName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateUser implements CreateUserUseCase {
    private UserCreator userCreator;

    public User execute(String name) throws UserAlreadyExistsException {
        return userCreator.create(new UserId(UUID.randomUUID()), new UserName(name));
    }
}