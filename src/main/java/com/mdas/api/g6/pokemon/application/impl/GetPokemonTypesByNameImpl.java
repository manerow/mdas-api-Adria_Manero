package com.mdas.api.g6.pokemon.application.impl;

import com.mdas.api.g6.pokemon.application.GetPokemonTypesByName;
import com.mdas.api.g6.pokemon.domain.exception.PokeApiConnectionErrorException;
import com.mdas.api.g6.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokemon.domain.services.PokemonDomainService;
import com.mdas.api.g6.pokemon.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokemon.domain.valueobject.PokemonTypes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetPokemonTypesByNameImpl implements GetPokemonTypesByName {

    private final PokemonDomainService pokemonDomainService;

    @Override
    public PokemonTypes execute(String name)
            throws PokemonNotFoundException, PokeApiConnectionErrorException {
        PokemonName pokemonName = new PokemonName(name);
        return pokemonDomainService.getPokemonTypesByName(pokemonName);
    }
}
