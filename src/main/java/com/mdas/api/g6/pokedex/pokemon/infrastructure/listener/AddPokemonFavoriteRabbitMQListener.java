package com.mdas.api.g6.pokedex.pokemon.infrastructure.listener;

import com.mdas.api.g6.pokedex.pokemon.application.IncrementPokemonFavoritesUseCase;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddPokemonFavoriteRabbitMQListener {

    private IncrementPokemonFavoritesUseCase incrementPokemonFavoritesUseCase;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "likes-queue", durable = "true"),
            exchange = @Exchange(value = "likes-exchange", type = "topic"),
            key = "pokemon.like"
    ))
    public void onAddPokemonFavoriteMessage(int pokemonId) {
        incrementPokemonFavoritesUseCase.execute(pokemonId);
    }
}
