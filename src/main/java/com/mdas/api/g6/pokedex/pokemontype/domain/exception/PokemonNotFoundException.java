package com.mdas.api.g6.pokedex.pokemontype.domain.exception;

public class PokemonNotFoundException extends Exception {
    public PokemonNotFoundException() {
        super("The pokemon with the provided name was not found.");
    }
}
