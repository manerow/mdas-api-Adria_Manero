package com.mdas.api.g6.user.application;

import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.exception.UserAlreadyExistsException;
import com.mdas.api.g6.user.domain.services.UserCreator;
import com.mdas.api.g6.user.domain.valueobject.FavouritePokemons;
import com.mdas.api.g6.user.domain.valueobject.UserName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserCreator userCreator;

    @Override
    public User execute(String name) throws UserAlreadyExistsException {
        User user = new User(new UserName(name), new FavouritePokemons(new HashSet<>()));
        userCreator.create(user);
        return user;
    }
}