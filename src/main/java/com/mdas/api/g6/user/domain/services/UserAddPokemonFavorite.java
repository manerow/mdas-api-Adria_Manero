package com.mdas.api.g6.user.domain.services;

import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.exception.PokemonAlreadyAddException;
import com.mdas.api.g6.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.domain.valueobject.FavoritePokemon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAddPokemonFavorite {

    private final UserRepository userRepository;

    public User addPokemonFavorite(User user, Integer pokemonId) throws PokemonAlreadyAddException {

        guard(user, pokemonId);
        user.getFavoritePokemons().getFavoritePokemons().add(new FavoritePokemon(pokemonId));
        userRepository.update(user);

        return user;
    }

    private void guard(User user, Integer pokemonId) throws PokemonAlreadyAddException {

        if (user.getFavoritePokemons().getFavoritePokemons().contains(new FavoritePokemon(pokemonId))) {
            throw new PokemonAlreadyAddException("Pokemon already add on list with Id: " + pokemonId);
        }
    }
}
