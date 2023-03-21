package com.mdas.api.g6.user.objectmother;

import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.valueobject.FavouritePokemon;
import com.mdas.api.g6.user.domain.valueobject.FavouritePokemons;
import com.mdas.api.g6.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.domain.valueobject.UserName;

import java.util.HashSet;
import java.util.UUID;

public class UserMother {

    public static User random() {
        return new User(UserIdMother.random(), UserNameMother.random(), FavouritePokemonsMother.random());
    }

    public static User random(UserId userId) {
        return new User(userId, UserNameMother.random(), FavouritePokemonsMother.random());
    }

    public static User random(UserName userName) {
        return new User(UserIdMother.random(), userName, FavouritePokemonsMother.random());
    }

    public static User random(FavouritePokemons favouritePokemons) {
        return new User(UserIdMother.random(), UserNameMother.random(), favouritePokemons);
    }

    public static User random(UserId userId, UserName userName) {
        return new User(userId, userName, FavouritePokemonsMother.random());
    }

    public static User random(UserId userId, UserName userName, FavouritePokemons favouritePokemons) {
        return new User(userId, userName, favouritePokemons);
    }
}
