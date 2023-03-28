package com.mdas.api.g6.pokedex.pokemonType.application;

import com.mdas.api.g6.pokedex.pokemonType.creator.PokemonCreator;
import com.mdas.api.g6.pokedex.pokemonType.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemonType.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemonType.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemonType.domain.services.PokemonSearcher;
import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class GetPokemonByNameUseCaseTest {

    @Mock
    private PokemonSearcher pokemonSearcher;

    @InjectMocks
    private GetPokemonByNameUseCase service;

    @Test
    public void shouldGetPokemonByName() throws PokemonNotFoundException, RepositoryUnavailableException {
        // Given
        Mockito.when(pokemonSearcher.getPokemonByName(Mockito.any(PokemonName.class)))
                .thenReturn(PokemonCreator.createPokemon());

        // When
        Pokemon result = service.execute("lucario");

        // Then
        Assertions.assertNotNull(result);
    }
}

