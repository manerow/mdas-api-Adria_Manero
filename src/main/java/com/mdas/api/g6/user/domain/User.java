package com.mdas.api.g6.user.domain;

import com.mdas.api.g6.user.domain.valueobject.FavouritePokemons;
import com.mdas.api.g6.user.domain.valueobject.UserName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
public class User {
    private UUID id;
    private UserName name;
    private FavouritePokemons favouritePokemons;
}
