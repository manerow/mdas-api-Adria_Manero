package com.mdas.api.g6;

import com.mdas.api.g6.pokemon.domain.valueobject.PokemonType;
import com.mdas.api.g6.pokemon.domain.valueobject.PokemonTypes;

import java.util.ArrayList;
import java.util.List;

public class PokemonCreator {

    public static PokemonType createPokemonType() {
        return new PokemonType("fighting");
    }

    public static PokemonTypes createPokemonTypes() {

        List<PokemonType> types = new ArrayList<>();
        types.add(createPokemonType());

        return new PokemonTypes(types);
    }
}
