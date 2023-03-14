package com.mdas.api.g6.pokemon.domain.services;

import com.mdas.api.g6.pokemon.domain.exception.PokeApiConnectionErrorException;
import com.mdas.api.g6.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokemon.domain.repository.PokemonRepositoryPort;
import com.mdas.api.g6.pokemon.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokemon.domain.valueobject.PokemonTypes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PokemonDomainService {
    private final PokemonRepositoryPort pokemonRepository;

    public PokemonTypes getPokemonTypesByName(PokemonName name)
            throws PokemonNotFoundException, PokeApiConnectionErrorException {
        return pokemonRepository.getPokemonByName(name).getTypes();
    }
}
