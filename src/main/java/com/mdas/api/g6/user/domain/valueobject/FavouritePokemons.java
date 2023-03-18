package com.mdas.api.g6.user.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FavouritePokemons {
    private Set<FavouritePokemon> favouritePokemons;
}
