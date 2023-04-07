package com.mdas.api.g6.user.user.infrastructure.eventbus;

import com.mdas.api.g6.user.user.domain.repository.UserEventPublisher;
import com.mdas.api.g6.user.user.domain.valueobject.PokemonId;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRabbitMqPublisher implements UserEventPublisher {

    private RabbitTemplate rabbitTemplate;

    @Override
    public void publishAddPokemonFavoriteEvent(PokemonId pokemonId) {
        rabbitTemplate.convertAndSend("likes-exchange", "pokemon.like", pokemonId.getId());
    }
}
