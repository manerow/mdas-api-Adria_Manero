package com.mdas.api.g6.user.user.domain.repository;

import com.mdas.api.g6.user.user.domain.valueobject.PokemonId;

public interface UserEventPublisher {
    void publishAddPokemonFavoriteEvent(PokemonId pokemonId);
}
