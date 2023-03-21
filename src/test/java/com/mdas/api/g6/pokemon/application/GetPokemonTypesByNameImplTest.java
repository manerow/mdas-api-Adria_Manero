package com.mdas.api.g6.pokemon.application;

import com.mdas.api.g6.pokedex.pokemon.application.GetPokemonByNameUseCase;
import com.mdas.api.g6.pokemon.creator.PokemonCreator;
import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemon.domain.services.PokemonDomainService;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class GetPokemonTypesByNameImplTest {


    GetPokemonByNameUseCase service;

    PokemonDomainService pokemonDomainService;

    private void initMock() {
        service = mock(GetPokemonByNameUseCase.class);
        pokemonDomainService = mock(PokemonDomainService.class);
    }

    private void when() throws PokemonNotFoundException, RepositoryUnavailableException {
        Mockito.when(pokemonDomainService.getPokemonByName(Mockito.any(PokemonName.class)))
                .thenReturn(PokemonCreator.createPokemon());

        service = new GetPokemonByNameUseCase(pokemonDomainService);
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
