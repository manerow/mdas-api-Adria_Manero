package com.mdas.api.g6.pokedex.pokemontype.infrastructure.pokeapi;

import com.mdas.api.g6.pokedex.pokemontype.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemontype.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemontype.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemontype.domain.repository.PokemonRepository;
import com.mdas.api.g6.pokedex.pokemontype.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokedex.pokemontype.infrastructure.pokeapi.entity.PokemonApiEntity;
import com.mdas.api.g6.pokedex.pokemontype.infrastructure.pokeapi.mapper.PokemonApiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;

@Component
@RequiredArgsConstructor
public class PokemonHttpRepository implements PokemonRepository {

    private RestTemplate restTemplate = new RestTemplate();
    private final PokemonApiMapper pokemonApiMapper;

    @Value("${pokeapi.url}")
    private String baseUrl;

    public Pokemon getPokemonByName(PokemonName pokemonName) throws PokemonNotFoundException, RepositoryUnavailableException {
        String url = baseUrl + "/pokemon/" + pokemonName.getName();
        ResponseEntity<PokemonApiEntity> response;
        try {
            response = restTemplate.getForEntity(url, PokemonApiEntity.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new PokemonNotFoundException();
        } catch (RestClientException e) {
            throw new RepositoryUnavailableException();
        }
        return pokemonApiMapper.toDomain(response.getBody());
    }
}
