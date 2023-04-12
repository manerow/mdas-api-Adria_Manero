package com.mdas.api.g6.user.user.domain.valueobject;

import com.mdas.api.g6.user.user.domain.exception.PokemonAlreadyAddException;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoritePokemons {
    private Set<PokemonId> favoritePokemons;

    public void add(PokemonId favoritePokemon) throws PokemonAlreadyAddException {
        guardPokemonAlreadyAdded(favoritePokemon);
        favoritePokemons.add(favoritePokemon);
    }
    private void guardPokemonAlreadyAdded(PokemonId favoritePokemon) throws PokemonAlreadyAddException {
        if (favoritePokemons.contains(favoritePokemon)) {
            throw new PokemonAlreadyAddException("Pokemon already add on list with Id: " + favoritePokemon.getId());
        }
    }
}
