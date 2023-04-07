package com.mdas.api.g6.pokedex.pokemon.application;

import com.mdas.api.g6.pokedex.pokemon.domain.services.AddPokemonFavorite;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncrementPokemonFavoritesUseCase {
    private AddPokemonFavorite addPokemonFavorite;

    public void execute(int pokemonId) {
        addPokemonFavorite.add(new PokemonId(pokemonId));
    }
}
