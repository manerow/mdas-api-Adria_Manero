package com.mdas.api.g6.user.application;

import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.exception.UserAlreadyExistsException;

public interface CreateUserUseCase {
    User execute(String name) throws UserAlreadyExistsException;
}
