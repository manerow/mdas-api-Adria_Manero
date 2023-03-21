package com.mdas.api.g6.pokedex.pokemon.domain.services;

import com.mdas.api.g6.pokedex.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemon.domain.repository.PokemonRepository;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PokemonSearcher {
    private final PokemonRepository pokemonRepository;

    public Pokemon getPokemonByName(PokemonName name)
            throws PokemonNotFoundException, RepositoryUnavailableException {
        return pokemonRepository.getPokemonByName(name);
    }
}
