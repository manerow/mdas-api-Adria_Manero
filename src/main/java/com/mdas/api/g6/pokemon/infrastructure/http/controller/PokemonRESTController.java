package com.mdas.api.g6.pokemon.infrastructure.http.controller;

import com.mdas.api.g6.pokemon.application.GetPokemonTypesByName;
import com.mdas.api.g6.pokemon.domain.exception.PokeApiConnectionErrorException;
import com.mdas.api.g6.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokemon.domain.valueobject.PokemonType;
import com.mdas.api.g6.pokemon.domain.valueobject.PokemonTypes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/pokemon")
@Slf4j
@RequiredArgsConstructor
public class PokemonRESTController {

    private final GetPokemonTypesByName getApiPokemonTypesByName;

    @GetMapping(value = "/getType")
    public ResponseEntity<String> getPokemon(@RequestParam(value = "pokemonName") String pokemonName) {
        PokemonTypes pokemonTypes;
        try {
            pokemonTypes = getApiPokemonTypesByName.execute(pokemonName);
        }  catch (PokemonNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (PokeApiConnectionErrorException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred trying to fetch pokemon types.");
        }

        String commaSeparatedTypes = pokemonTypes.getTypes()
                .stream()
                .map(PokemonType::getName)
                .collect(Collectors.joining(", "));
        return ResponseEntity.ok(commaSeparatedTypes);
    }
}
