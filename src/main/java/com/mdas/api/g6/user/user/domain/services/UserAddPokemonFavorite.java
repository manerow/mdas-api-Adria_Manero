package com.mdas.api.g6.user.user.domain.services;

import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.domain.exception.PokemonAlreadyAddException;
import com.mdas.api.g6.user.user.domain.repository.UserEventPublisher;
import com.mdas.api.g6.user.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.user.domain.valueobject.PokemonId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAddPokemonFavorite {

    private final UserRepository userRepository;
    private final UserEventPublisher userEventPublisher;

    public User addPokemonFavorite(User user, PokemonId pokemonId) throws PokemonAlreadyAddException {
        user.addFavoritePokemon(pokemonId);
        userRepository.save(user);
        userEventPublisher.publishAddPokemonFavoriteEvent(pokemonId);
        return user;
    }
}
