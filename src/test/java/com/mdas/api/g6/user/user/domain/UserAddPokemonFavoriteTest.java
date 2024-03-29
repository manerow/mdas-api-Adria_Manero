package com.mdas.api.g6.user.user.domain;

import com.mdas.api.g6.user.user.domain.exception.PokemonAlreadyAddException;
import com.mdas.api.g6.user.user.domain.exception.UserAlreadyExistsException;
import com.mdas.api.g6.user.user.domain.repository.UserEventPublisher;
import com.mdas.api.g6.user.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.user.domain.services.UserAddPokemonFavorite;
import com.mdas.api.g6.user.user.domain.services.UserCreator;
import com.mdas.api.g6.user.user.domain.valueobject.PokemonId;
import com.mdas.api.g6.user.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.user.domain.valueobject.UserName;
import com.mdas.api.g6.user.user.infrastructure.eventbus.UserRabbitMqPublisher;
import com.mdas.api.g6.user.user.objectmother.UserIdMother;
import com.mdas.api.g6.user.user.objectmother.UserNameMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserAddPokemonFavoriteTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private UserRabbitMqPublisher userEventPublisher;

    @InjectMocks
    private UserCreator userCreator;

    @InjectMocks
    private UserAddPokemonFavorite userAddPokemonFavorite;

    @Test
    void shouldUserAddPokemonFavoriteSuccessfully() throws UserAlreadyExistsException, PokemonAlreadyAddException {

        // GIVEN
        UserId userId = UserIdMother.random();
        UserName userName = UserNameMother.random();

        //WHEN
        when(userRepositoryMock.existsById(userId)).thenReturn(false);

        User objUser = userCreator.create(userId, userName);
        objUser.getFavoritePokemons().getFavoritePokemons().add(new PokemonId(23));

        User result = userAddPokemonFavorite.addPokemonFavorite(objUser,new PokemonId(26));

        verify(userEventPublisher, times(1)).publishAddPokemonFavoriteEvent(Mockito.any());
        assertTrue(result.getFavoritePokemons().getFavoritePokemons().contains(new PokemonId(26)));
    }

    @Test
    void shouldThrowPokemonAlreadyAdd() throws UserAlreadyExistsException {

        // GIVEN
        UserId userId = UserIdMother.random();
        UserName userName = UserNameMother.random();

        // WHEN
        when(userRepositoryMock.existsById(userId)).thenReturn(false);

        User objUser = userCreator.create(userId, userName);
        objUser.getFavoritePokemons().getFavoritePokemons().add(new PokemonId(23));

        assertThrows(PokemonAlreadyAddException.class, () -> userAddPokemonFavorite.addPokemonFavorite(objUser,new PokemonId(23)));
    }

}
