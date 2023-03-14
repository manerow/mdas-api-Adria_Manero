package com.mdas.api.g6.pokemon.domain.repository;

import com.mdas.api.g6.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokemon.domain.exception.PokeApiConnectionErrorException;
import com.mdas.api.g6.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokemon.domain.valueobject.PokemonName;

public interface PokemonRepositoryPort {
    Pokemon getPokemonByName(PokemonName name) throws PokemonNotFoundException, PokeApiConnectionErrorException;
}
