package com.mdas.api.g6.user.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class FavouritePokemons {
    private Set<Integer> favouritePokemonList;
}
