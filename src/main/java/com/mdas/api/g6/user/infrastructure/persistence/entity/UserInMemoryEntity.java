package com.mdas.api.g6.user.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class UserInMemoryEntity {
    private UUID id;

    private String name;

    private Set<Integer> favouritePokemons;
}
