package com.mdas.api.g6.user.user.infrastructure.eventbus;

import com.mdas.api.g6.user.user.domain.repository.UserEventPublisher;
import com.mdas.api.g6.user.user.domain.valueobject.PokemonId;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRabbitMqPublisher implements UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publishAddPokemonFavoriteEvent(PokemonId pokemonId) {
        rabbitTemplate.convertAndSend("likes-exchange", "pokemon.like", pokemonId.getId());
    }
}
