package com.mdas.api.g6.user.user.domain.services;

import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.domain.exception.UserAlreadyExistsException;
import com.mdas.api.g6.user.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.user.domain.valueobject.FavoritePokemons;
import com.mdas.api.g6.user.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.user.domain.valueobject.UserName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;


@Component
@RequiredArgsConstructor
public class UserCreator {

    private final UserRepository userRepository;

    public User create(UserId userId, UserName userName) throws UserAlreadyExistsException {
        guard(userId);
        User user = new User(userId, userName, new FavoritePokemons(new HashSet<>()));
        userRepository.save(user);
        return user;
    }

    private void guard(UserId userId) throws UserAlreadyExistsException {
        if (userRepository.existsById(userId)) {
            throw new UserAlreadyExistsException("User already exists with id: " + userId.getId());
        }
    }
}
