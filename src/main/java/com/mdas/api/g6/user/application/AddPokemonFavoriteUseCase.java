package com.mdas.api.g6.user.application;

import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.exception.PokemonAlreadyAddException;
import com.mdas.api.g6.user.domain.exception.UserNotFoundException;
import com.mdas.api.g6.user.infrastructure.controller.dto.AddFavoritePokemonRequest;

public interface AddPokemonFavoriteUseCase {

    User execute(String userId, AddFavoritePokemonRequest request) throws PokemonAlreadyAddException, UserNotFoundException;
}
