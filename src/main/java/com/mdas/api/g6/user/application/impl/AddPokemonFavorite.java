package com.mdas.api.g6.user.application.impl;

import com.mdas.api.g6.user.application.AddPokemonFavoriteUseCase;
import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.exception.PokemonAlreadyAddException;
import com.mdas.api.g6.user.domain.exception.UserNotFoundException;
import com.mdas.api.g6.user.domain.services.UserAddPokemonFavorite;
import com.mdas.api.g6.user.domain.services.UserFinder;
import com.mdas.api.g6.user.infrastructure.controller.dto.AddFavoritePokemonRequest;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddPokemonFavorite implements AddPokemonFavoriteUseCase {

    private final UserAddPokemonFavorite userAddPokemonFavorite;
    private final UserFinder userFinder;

    @Override
    public User execute(String userId, AddFavoritePokemonRequest request)
            throws PokemonAlreadyAddException, UserNotFoundException {

        UUID uuid = UUID.fromString(userId);
        return userAddPokemonFavorite.addPokemonFavorite(userFinder.getUserById(uuid), request.getPokemonId());
    }
}
