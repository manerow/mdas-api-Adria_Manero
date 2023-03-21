package com.mdas.api.g6.pokemon.application.impl;

import com.mdas.api.g6.pokemon.application.GetPokemonByName;
import com.mdas.api.g6.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokemon.domain.services.PokemonDomainService;
import com.mdas.api.g6.pokemon.domain.valueobject.PokemonName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetPokemonTypesByNameImpl implements GetPokemonByName {

    private final PokemonDomainService pokemonDomainService;

    @Override
    public Pokemon execute(String name)
            throws PokemonNotFoundException, RepositoryUnavailableException {
        PokemonName pokemonName = new PokemonName(name);
        return pokemonDomainService.getPokemonByName(pokemonName);
    }
}
