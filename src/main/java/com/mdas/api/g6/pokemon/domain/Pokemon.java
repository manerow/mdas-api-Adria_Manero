package com.mdas.api.g6.pokemon.domain;

import com.mdas.api.g6.pokemon.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokemon.domain.valueobject.PokemonTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Pokemon {
    private Integer id;
    private PokemonName name;
    private PokemonTypes types;
}
