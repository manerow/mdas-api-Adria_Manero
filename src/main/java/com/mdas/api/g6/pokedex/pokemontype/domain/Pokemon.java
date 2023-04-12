package com.mdas.api.g6.pokedex.pokemontype.domain;

import com.mdas.api.g6.pokedex.pokemontype.domain.valueobject.PokemonId;
import com.mdas.api.g6.pokedex.pokemontype.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokedex.pokemontype.domain.valueobject.PokemonTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Pokemon {
    private PokemonId id;
    private PokemonName name;
    private PokemonTypes types;
}
