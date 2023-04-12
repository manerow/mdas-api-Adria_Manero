package com.mdas.api.g6.pokedex.pokemontype.domain.services;

import com.mdas.api.g6.pokedex.pokemontype.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemontype.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemontype.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemontype.domain.repository.PokemonRepository;
import com.mdas.api.g6.pokedex.pokemontype.domain.valueobject.PokemonName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PokemonSearcher {
    private final PokemonRepository pokemonRepository;

    public Pokemon getPokemonByName(PokemonName name)
            throws PokemonNotFoundException, RepositoryUnavailableException {
        return pokemonRepository.getPokemonByName(name);
    }
}
