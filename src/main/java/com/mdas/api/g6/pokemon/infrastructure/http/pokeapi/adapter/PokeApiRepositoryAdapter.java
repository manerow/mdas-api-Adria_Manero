package com.mdas.api.g6.pokemon.infrastructure.http.pokeapi.adapter;

import com.mdas.api.g6.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokemon.domain.repository.PokemonRepositoryPort;
import com.mdas.api.g6.pokemon.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokemon.infrastructure.http.pokeapi.repository.PokemonHttpRepository;
import com.mdas.api.g6.pokemon.infrastructure.http.pokeapi.entity.PokemonApiEntity;
import com.mdas.api.g6.pokemon.infrastructure.http.pokeapi.mapper.PokemonApiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PokeApiRepositoryAdapter implements PokemonRepositoryPort {

    private final PokemonHttpRepository pokeApiHttpRepository;
    private final PokemonApiMapper pokemonApiMapper;

    @Override
    public Pokemon getPokemonByName(PokemonName name) throws PokemonNotFoundException, RepositoryUnavailableException {
        PokemonApiEntity pokeApiPokemonEntity = pokeApiHttpRepository.getPokemonByName(name);
        return pokemonApiMapper.toDomain(pokeApiPokemonEntity);
    }
}
