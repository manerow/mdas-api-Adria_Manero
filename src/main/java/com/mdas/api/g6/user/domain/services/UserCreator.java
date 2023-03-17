package com.mdas.api.g6.user.domain.services;

import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.exception.UserAlreadyExistsException;
import com.mdas.api.g6.user.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserCreator {

    private UserRepository userRepository;

    public void create(User user) throws UserAlreadyExistsException {
        guard(user.getName().getName());
        userRepository.save(user);
    }

    private void guard(String userName) throws UserAlreadyExistsException {
        if (userRepository.existsByName(userName)) {
            throw new UserAlreadyExistsException("The username '" + userName + "' already exists");
        }
    }
}
