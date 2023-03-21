package com.mdas.api.g6.user.application;

import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.exception.PokemonAlreadyAddException;
import com.mdas.api.g6.user.domain.exception.UserNotFoundException;
import com.mdas.api.g6.user.domain.services.UserAddPokemonFavorite;
import com.mdas.api.g6.user.domain.services.UserFinder;
import com.mdas.api.g6.user.infrastructure.controller.dto.AddFavoritePokemonRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddPokemonFavoriteUseCase {

    private final UserAddPokemonFavorite userAddPokemonFavorite;
    private final UserFinder userFinder;

    public User execute(String userId, AddFavoritePokemonRequest request)
            throws PokemonAlreadyAddException, UserNotFoundException {

        UUID uuid = UUID.fromString(userId);
        return userAddPokemonFavorite.addPokemonFavorite(userFinder.getUserById(uuid), request.getPokemonId());
    }
}
