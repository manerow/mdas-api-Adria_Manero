package com.mdas.api.g6.pokedex.pokemon.domain.services;

import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemon.domain.repository.PokemonRepository;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonId;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PokemonFinder {
    private final PokemonRepository pokeRepository;

    public Pokemon find(PokemonId id)
            throws PokemonNotFoundException, RepositoryUnavailableException {
        return pokeRepository.getPokemonById(id);
    }
}
