package com.mdas.api.g6.pokedex.pokemonType.domain.services;

import com.mdas.api.g6.pokedex.pokemonType.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemonType.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemonType.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemonType.domain.repository.PokemonRepository;
import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonName;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class PokemonSearcher {
    private final PokemonRepository pokemonRepository;

    public Pokemon getPokemonByName(PokemonName name)
            throws PokemonNotFoundException, RepositoryUnavailableException {
        return pokemonRepository.getPokemonByName(name);
    }
}
