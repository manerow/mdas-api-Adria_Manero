package com.mdas.api.g6.pokedex.pokemontype.domain.exception;

public class RepositoryUnavailableException extends Exception {
    public RepositoryUnavailableException() {
        super("The application could not reach PokeAPI or the connection timed out.");
    }
}
