package com.mdas.api.g6.pokedex.pokemontype.objectmother;

import com.github.javafaker.Faker;
import com.mdas.api.g6.pokedex.pokemontype.domain.valueobject.PokemonType;

public class PokemonTypeMother {
    public static PokemonType random() {
        return  new PokemonType(Faker.instance().color().name());
    }

    public static PokemonType random(String pokemonName) {
        return  new PokemonType(pokemonName);
    }
}
