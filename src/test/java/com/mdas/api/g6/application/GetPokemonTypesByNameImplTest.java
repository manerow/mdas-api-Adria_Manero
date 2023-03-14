package com.mdas.api.g6.application;

import com.mdas.api.g6.PokemonCreator;
import com.mdas.api.g6.pokemon.application.impl.GetPokemonTypesByNameImpl;
import com.mdas.api.g6.pokemon.domain.exception.PokeApiConnectionErrorException;
import com.mdas.api.g6.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokemon.domain.services.PokemonDomainService;
import com.mdas.api.g6.pokemon.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokemon.domain.valueobject.PokemonTypes;
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


    GetPokemonTypesByNameImpl service;

    PokemonDomainService pokemonDomainService;

    private void initMock() {
        service = mock(GetPokemonTypesByNameImpl.class);
        pokemonDomainService = mock(PokemonDomainService.class);
    }

    private void when() throws PokemonNotFoundException, PokeApiConnectionErrorException {
        Mockito.when(pokemonDomainService.getPokemonTypesByName(Mockito.any(PokemonName.class)))
                .thenReturn(PokemonCreator.createPokemonTypes());

        service = new GetPokemonTypesByNameImpl(pokemonDomainService);
    }

    @Test
    @DisplayName("test1")
    public void test1 () throws PokemonNotFoundException, PokeApiConnectionErrorException {
        initMock();
        when();

        PokemonTypes result = service.execute("lucario");
        Assertions.assertNotNull(result);

    }


}