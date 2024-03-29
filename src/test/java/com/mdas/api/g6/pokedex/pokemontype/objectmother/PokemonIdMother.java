package com.mdas.api.g6.pokedex.pokemontype.objectmother;

import com.github.javafaker.Faker;
import com.mdas.api.g6.pokedex.pokemontype.domain.valueobject.PokemonId;

public class PokemonIdMother {
    public static PokemonId random() {
        return new PokemonId(Faker.instance().number().randomDigit());
    }

    public static PokemonId random(Integer pokemonId) {
        return new PokemonId(pokemonId);
    }
}
