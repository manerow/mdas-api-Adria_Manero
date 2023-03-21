package com.mdas.api.g6.pokemon.infrastructure.http.controller;

import com.mdas.api.g6.pokemon.application.GetPokemonByName;
import com.mdas.api.g6.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokemon.domain.exception.PokeApiConnectionErrorException;
import com.mdas.api.g6.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.shared.infrastructure.controller.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemon")
@Slf4j
@RequiredArgsConstructor
public class PokemonRESTController {

    private final GetPokemonByName getApiPokemonTypesByName;

    @GetMapping(value = "/getType")
    public ResponseEntity<ApiResponse<Pokemon>> getPokemon(@RequestParam(value = "pokemonName") String pokemonName) {
        Pokemon pokemon;
        try {
            pokemon = getApiPokemonTypesByName.execute(pokemonName);
        }  catch (PokemonNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND, e.getMessage(), null));
        } catch (PokeApiConnectionErrorException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                    .body(new ApiResponse<>(HttpStatus.REQUEST_TIMEOUT, e.getMessage(), null));
        } catch (Exception e) {
            String errorMessage = "Unexpected error occurred trying to fetch pokemon types.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, null));
        }

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, null, pokemon));
    }
}
