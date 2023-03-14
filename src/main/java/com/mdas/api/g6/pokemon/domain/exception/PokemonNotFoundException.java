package com.mdas.api.g6.pokemon.domain.exception;

public class PokemonNotFoundException extends Exception {
    public PokemonNotFoundException() {
        super("The pokemon with the provided name was not found.");
    }
}
