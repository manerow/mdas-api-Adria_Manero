package com.mdas.api.g6.pokemon.domain.exception;

public class PokeApiConnectionErrorException extends Exception {
    public PokeApiConnectionErrorException() {
        super("The application could not reach PokeAPI or the connection timed out.");
    }
}
