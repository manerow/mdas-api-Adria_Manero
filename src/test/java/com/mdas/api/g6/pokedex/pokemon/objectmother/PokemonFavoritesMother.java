package com.mdas.api.g6.pokedex.pokemon.objectmother;

import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonFavorites;

import java.util.Random;

public class PokemonFavoritesMother {
    public static PokemonFavorites random() {
        Random random = new Random();
        int favorites = random.nextInt(100) + 1;
        return new PokemonFavorites(favorites);
    }

    public static PokemonFavorites random(int favorites) {
            return new PokemonFavorites(favorites);
        }
}
