package com.mdas.api.g6.pokedex.pokemon.infrastructure.pokeapi.mapper;

import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.*;
import com.mdas.api.g6.pokedex.pokemon.infrastructure.pokeapi.entity.PokemonApiEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PokemonMapper {
    @Mapping(target = "id", qualifiedByName = "mapId")
    @Mapping(target = "name", qualifiedByName = "mapName")
    @Mapping(target = "height", qualifiedByName = "mapHeight")
    @Mapping(target = "weight", qualifiedByName = "mapWeight")
    @Mapping(target = "favorites", qualifiedByName = "mapFavorites")
    Pokemon toDomain(PokemonApiEntity pokemonApiEntity);

    @Named("mapId")
    default PokemonId mapId(Integer id) {
        return new PokemonId(id);
    }

    @Named("mapName")
    default PokemonName mapName(String name) {
        return new PokemonName(name);
    }

    @Named("mapHeight")
    default PokemonHeight mapHeight(double height) {
        return new PokemonHeight(height);
    }

    @Named("mapWeight")
    default PokemonWeight mapWeight(double weight) {
        return new PokemonWeight(weight);
    }

    @Named("mapFavorites")
    default PokemonFavorites mapFavorites(int favorites) {
        return new PokemonFavorites(favorites);
    }
}


