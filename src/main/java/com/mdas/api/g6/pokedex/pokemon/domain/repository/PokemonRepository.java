package com.mdas.api.g6.pokedex.pokemon.domain.repository;

import com.mdas.api.g6.pokedex.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.PokemonNotFoundException;

public interface PokemonRepository {
    Pokemon getPokemonByName(PokemonName name) throws PokemonNotFoundException, RepositoryUnavailableException;
}
