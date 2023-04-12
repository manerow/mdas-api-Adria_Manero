package com.mdas.api.g6.user.user.objectmother;

import com.github.javafaker.Faker;
import com.mdas.api.g6.user.user.domain.valueobject.PokemonId;
import com.mdas.api.g6.user.user.domain.valueobject.FavoritePokemons;

import java.util.HashSet;
import java.util.Set;

public class FavoritePokemonsMother {

    public static FavoritePokemons random() {
        Faker faker = new Faker();
        Set<PokemonId> favoritePokemons = new HashSet<>();

        int iterations = faker.number().numberBetween(3, 8);
        for (int i = 0; i < iterations; i++) {
            PokemonId favoritePokemon = new PokemonId(faker.number().numberBetween(1, 100));
            favoritePokemons.add(favoritePokemon);
        }
        return new FavoritePokemons(favoritePokemons);
    }

    public static FavoritePokemons random(HashSet<PokemonId> favoritePokemons) {
        return new FavoritePokemons(favoritePokemons);
    }
}
