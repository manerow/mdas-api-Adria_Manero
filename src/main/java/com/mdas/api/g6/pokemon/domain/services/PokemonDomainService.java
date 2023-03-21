package com.mdas.api.g6.pokemon.domain.services;

import com.mdas.api.g6.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokemon.domain.repository.PokemonRepositoryPort;
import com.mdas.api.g6.pokemon.domain.valueobject.PokemonName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PokemonDomainService {
    private final PokemonRepositoryPort pokemonRepository;

    public Pokemon getPokemonByName(PokemonName name)
            throws PokemonNotFoundException, RepositoryUnavailableException {
        return pokemonRepository.getPokemonByName(name);
    }
}
