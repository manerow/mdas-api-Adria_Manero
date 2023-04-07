package com.mdas.api.g6.pokedex.pokemon.domain.services;

import com.mdas.api.g6.pokedex.pokemon.domain.repository.PokemonRepository;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddPokemonFavorite {
    private final PokemonRepository pokemonRepository;

    public void add(PokemonId pokemonId) {
        pokemonRepository.addPokemonFavorite(pokemonId);
    }
}
