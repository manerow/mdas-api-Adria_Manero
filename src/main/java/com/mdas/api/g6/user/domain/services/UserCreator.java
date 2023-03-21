package com.mdas.api.g6.user.domain.services;

import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.exception.UserAlreadyExistsException;
import com.mdas.api.g6.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.domain.valueobject.FavoritePokemons;
import com.mdas.api.g6.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.domain.valueobject.UserName;
import java.util.HashSet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class UserCreator {

    private UserRepository userRepository;

    public User create(UserId id, UserName userName) throws UserAlreadyExistsException {
        guard(userName.getName());
        User user = new User(id, userName, new FavoritePokemons(new HashSet<>()));
        userRepository.save(user);
        return user;
    }

    private void guard(String userName) throws UserAlreadyExistsException {
        if (userRepository.existsByName(userName)) {
            throw new UserAlreadyExistsException("User already exists with name: " + userName);
        }
    }
}
