package com.mdas.api.g6.user.infrastructure.persistence.mapper;

import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.valueobject.FavouritePokemon;
import com.mdas.api.g6.user.domain.valueobject.FavouritePokemons;
import com.mdas.api.g6.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.domain.valueobject.UserName;
import com.mdas.api.g6.user.infrastructure.persistence.entity.UserInMemoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserInMemoryMapper {
    @Mapping(target = "id", qualifiedByName = "mapIdToDomain")
    @Mapping(target = "name", qualifiedByName = "mapNameToDomain")
    @Mapping(target = "favouritePokemons", qualifiedByName = "mapFavouritePokemonsToDomain")
    User toDomain(UserInMemoryEntity userInMemoryEntity);
    @Mapping(target = "id", qualifiedByName = "mapIdToEntity")
    @Mapping(target = "name", qualifiedByName = "mapNameToEntity")
    @Mapping(target = "favouritePokemons", qualifiedByName = "mapFavouritePokemonsToEntity")
    UserInMemoryEntity toEntity(User user);

    @Named("mapIdToDomain")
    default UserId mapIdToDomain(UUID id) {
        return new UserId(id);
    }

    @Named("mapNameToDomain")
    default UserName mapNameToDomain(String name) {
        return new UserName(name);
    }

    @Named("mapFavouritePokemonsToDomain")
    default FavouritePokemons mapFavouritePokemonsToDomain(Set<Integer> favouritePokemons) {
        Set<FavouritePokemon> favouritePokemonsSet = favouritePokemons.stream()
                .map(FavouritePokemon::new)
                .collect(Collectors.toSet());
        return new FavouritePokemons(favouritePokemonsSet);
    }

    @Named("mapIdToEntity")
    default UUID mapIdToEntity(UserId userId) {
        return userId.getId();
    }

    @Named("mapNameToEntity")
    default String mapNameToEntity(UserName userName) {
        return userName.getName();
    }

    @Named("mapFavouritePokemonsToEntity")
    default Set<Integer> mapFavouritePokemonsToEntity(FavouritePokemons favouritePokemons) {
        return favouritePokemons.getFavouritePokemons().stream()
                .map(FavouritePokemon::getId)
                .collect(Collectors.toSet());
    }
}


