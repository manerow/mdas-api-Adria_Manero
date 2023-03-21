package com.mdas.api.g6.user.domain.valueobject;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoritePokemons {
    private Set<FavoritePokemon> favoritePokemons;
}
