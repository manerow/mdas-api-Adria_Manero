package com.mdas.api.g6.pokemon.creator;

import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonType;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonTypes;

import java.util.ArrayList;
import java.util.List;

public class PokemonCreator {

    public static PokemonType createPokemonType() {
        return new PokemonType("fighting");
    }

    public static Pokemon createPokemon() {

        List<PokemonType> types = new ArrayList<>();
        types.add(createPokemonType());

        return new Pokemon(1, new PokemonName("lucario"), new PokemonTypes(types));
    }
}
