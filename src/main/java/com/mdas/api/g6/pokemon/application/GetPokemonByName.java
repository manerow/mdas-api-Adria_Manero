package com.mdas.api.g6.pokemon.application;

import com.mdas.api.g6.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokemon.domain.Pokemon;

public interface GetPokemonByName {
    Pokemon execute(String name) throws PokemonNotFoundException, RepositoryUnavailableException;
}
