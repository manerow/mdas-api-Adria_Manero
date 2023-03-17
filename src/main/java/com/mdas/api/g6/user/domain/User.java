package com.mdas.api.g6.user.domain;

import com.mdas.api.g6.user.domain.valueobject.FavouritePokemons;
import com.mdas.api.g6.user.domain.valueobject.UserName;
import lombok.Getter;

import java.util.UUID;
@Getter
public class User {
    private UUID id;
    private UserName name;
    private FavouritePokemons favouritePokemons;

    public User(UserName name, FavouritePokemons favouritePokemons) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.favouritePokemons = favouritePokemons;
    }
}
