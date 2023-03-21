package com.mdas.api.g6.pokemon.application;

import com.mdas.api.g6.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokemon.domain.services.PokemonDomainService;
import com.mdas.api.g6.pokemon.domain.valueobject.PokemonName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetPokemonByNameUseCase {

    private final PokemonDomainService pokemonDomainService;

    public Pokemon execute(String name)
            throws PokemonNotFoundException, RepositoryUnavailableException {
        PokemonName pokemonName = new PokemonName(name);
        return pokemonDomainService.getPokemonByName(pokemonName);
    }
}
