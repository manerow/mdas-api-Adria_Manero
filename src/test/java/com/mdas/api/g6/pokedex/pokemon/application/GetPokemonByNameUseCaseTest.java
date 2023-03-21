package com.mdas.api.g6.pokedex.pokemon.application;

import com.mdas.api.g6.pokedex.pokemon.creator.PokemonCreator;
import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemon.domain.services.PokemonSearcher;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class GetPokemonByNameUseCaseTest {


    GetPokemonByNameUseCase service;

    PokemonSearcher pokemonSearcher;

    private void initMock() {
        service = mock(GetPokemonByNameUseCase.class);
        pokemonSearcher = mock(PokemonSearcher.class);
    }

    private void when() throws PokemonNotFoundException, RepositoryUnavailableException {
        Mockito.when(pokemonSearcher.getPokemonByName(Mockito.any(PokemonName.class)))
                .thenReturn(PokemonCreator.createPokemon());

        service = new GetPokemonByNameUseCase(pokemonSearcher);
    }

    @Test
    @DisplayName("test1")
    public void test1 () throws PokemonNotFoundException, RepositoryUnavailableException {
        initMock();
        when();

        Pokemon result = service.execute("lucario");
        Assertions.assertNotNull(result);

    }


}
