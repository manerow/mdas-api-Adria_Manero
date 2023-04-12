package com.mdas.api.g6.pokedex.pokemontype.domain.repository;

import com.mdas.api.g6.pokedex.pokemontype.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemontype.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokedex.pokemontype.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemontype.domain.exception.PokemonNotFoundException;

public interface PokemonRepository {
    Pokemon getPokemonByName(PokemonName name) throws PokemonNotFoundException, RepositoryUnavailableException;
}
