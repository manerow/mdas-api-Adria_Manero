package com.mdas.api.g6.pokedex.pokemon.infrastructure.pokeapi.adapter;

import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemon.domain.repository.PokemonRepository;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonId;
import com.mdas.api.g6.pokedex.pokemon.infrastructure.pokeapi.entity.PokemonApiEntity;
import com.mdas.api.g6.pokedex.pokemon.infrastructure.pokeapi.mapper.PokemonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PokemonPokeApiRepository implements PokemonRepository {
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${pokeapi.url}")
    private String baseUrl;
    private Map<Integer, Integer> favoritePokemons = new HashMap<>();

    private final PokemonMapper pokemonMapper;

    @Override
    public Pokemon getPokemonById(PokemonId pokemonId) throws PokemonNotFoundException, RepositoryUnavailableException {
        String url = baseUrl + "/pokemon/" + pokemonId.getId();
        ResponseEntity<PokemonApiEntity> response;
        try {
            response = restTemplate.getForEntity(url, PokemonApiEntity.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new PokemonNotFoundException();
        } catch (RestClientException e) {
            throw new RepositoryUnavailableException();
        }

        Integer favorites = favoritePokemons.get(pokemonId.getId());
        if (favorites == null) {
            favorites = 0;
        }
        PokemonApiEntity pokemonApiEntity = response.getBody();
        pokemonApiEntity.setFavorites(favorites);
        return pokemonMapper.toDomain(response.getBody());
    }

    @Override
    public void addPokemonFavorite(PokemonId pokemonId) {
        Integer favorites = favoritePokemons.get(pokemonId.getId());
        if (favorites == null) {
            favorites = 1;
        } else {
            favorites++;
        }
        favoritePokemons.put(pokemonId.getId(), favorites);
    }
}
