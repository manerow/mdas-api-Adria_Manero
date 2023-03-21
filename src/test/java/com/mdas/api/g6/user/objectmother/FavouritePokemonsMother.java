package com.mdas.api.g6.user.objectmother;

import com.github.javafaker.Faker;
import com.mdas.api.g6.user.domain.valueobject.FavouritePokemon;
import com.mdas.api.g6.user.domain.valueobject.FavouritePokemons;

import java.util.HashSet;
import java.util.Set;

public class FavouritePokemonsMother {

    public static FavouritePokemons random() {
        Faker faker = new Faker();
        Set<FavouritePokemon> favouritePokemons = new HashSet<>();

        int iterations = faker.number().numberBetween(3, 8);
        for (int i = 0; i < iterations; i++) {
            FavouritePokemon favouritePokemon = new FavouritePokemon(faker.number().numberBetween(1, 100));
            favouritePokemons.add(favouritePokemon);
        }
        return new FavouritePokemons(favouritePokemons);
    }

    public static FavouritePokemons random(HashSet<FavouritePokemon> favouritePokemons) {
        return new FavouritePokemons(favouritePokemons);
    }
}
