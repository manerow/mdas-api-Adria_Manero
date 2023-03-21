package com.mdas.api.g6.user.user.domain.services;

import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.domain.exception.UserNotFoundException;
import com.mdas.api.g6.user.user.domain.repository.UserRepository;
import java.util.UUID;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserFinder {

    private UserRepository userRepository;
    public User getUserById(UUID userId) throws UserNotFoundException {
        User result = userRepository.getUserById(userId);
        guard(userId, result);

        return result;
    }

    private void guard(UUID userId, User result) throws UserNotFoundException {
        if (result == null) {
            throw new UserNotFoundException("User not found: " + userId.toString());
        }
    }


}
