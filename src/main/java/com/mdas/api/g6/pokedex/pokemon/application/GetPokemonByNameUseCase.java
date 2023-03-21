package com.mdas.api.g6.pokedex.pokemon.application;

import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemon.domain.services.PokemonSearcher;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.PokemonNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetPokemonByNameUseCase {

    private final PokemonSearcher pokemonDomainService;

    public Pokemon execute(String name)
            throws PokemonNotFoundException, RepositoryUnavailableException {
        PokemonName pokemonName = new PokemonName(name);
        return pokemonDomainService.getPokemonByName(pokemonName);
    }
}
