package com.mdas.api.g6.pokedex.pokemonType.objectmother;

import com.github.javafaker.Faker;
import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonName;

public class PokemonNameMother {
    public static PokemonName random() {
        return  new PokemonName(Faker.instance().pokemon().name());
    }

    public static PokemonName random(String pokemonName) {
        return  new PokemonName(pokemonName);
    }
}
