package com.mdas.api.g6.pokedex.pokemontype.objectmother;

import com.mdas.api.g6.pokedex.pokemontype.domain.Pokemon;

public class PokemonMother {
    public static Pokemon random() {
        return new Pokemon(
                PokemonIdMother.random(),
                PokemonNameMother.random(),
                PokemonTypesMother.random()
        );
    }
}
