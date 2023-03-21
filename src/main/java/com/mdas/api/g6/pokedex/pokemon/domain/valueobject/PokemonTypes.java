package com.mdas.api.g6.pokedex.pokemon.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class PokemonTypes {
    private List<PokemonType> types;
}
