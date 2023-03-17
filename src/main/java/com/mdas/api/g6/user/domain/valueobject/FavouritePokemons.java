package com.mdas.api.g6.user.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class FavouritePokemons {
    private Set<PokemonId> favouritePokemons;
}
