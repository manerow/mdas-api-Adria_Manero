package com.mdas.api.g6.user.application;

import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.exception.UserAlreadyExistsException;
import com.mdas.api.g6.user.domain.services.UserCreator;
import com.mdas.api.g6.user.domain.valueobject.FavouritePokemons;
import com.mdas.api.g6.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.domain.valueobject.UserName;
import com.mdas.api.g6.user.objectmother.FavouritePokemonsMother;
import com.mdas.api.g6.user.objectmother.UserIdMother;
import com.mdas.api.g6.user.objectmother.UserMother;
import com.mdas.api.g6.user.objectmother.UserNameMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    @Mock
    private UserCreator userCreatorMock;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @Test
    void shouldCreateUserSuccessfully() throws UserAlreadyExistsException {
        // GIVEN
        UserId userId = UserIdMother.random();
        UserName userName = UserNameMother.random();
        FavouritePokemons favouritePokemons = FavouritePokemonsMother.random(new HashSet<>());
        User user = UserMother.random(userId, userName, new FavouritePokemons(new HashSet<>()));
        when(userCreatorMock.create(any(UserId.class), any(UserName.class))).thenReturn(user);

        // WHEN
        User createdUser = createUserUseCase.execute(userName.getName());

        // THEN
        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals(userName.getName(), createdUser.getName().getName());
        assertTrue(createdUser.getFavouritePokemons().getFavouritePokemons().isEmpty());
        verify(userCreatorMock, times(1)).create(any(UserId.class), any(UserName.class));
    }

    @Test()
    void shouldThrowExceptionWhenUserAlreadyExists() throws UserAlreadyExistsException {
        // Given
        UserName userName = UserNameMother.random();
        doThrow(UserAlreadyExistsException.class).when(userCreatorMock).create(any(UserId.class), any(UserName.class));

        // When
        Throwable exception = assertThrows(UserAlreadyExistsException.class, () -> {
            createUserUseCase.execute(userName.getName());
        });

        // Then
        assertNotNull(exception);
        verify(userCreatorMock, times(1)).create(any(UserId.class), any(UserName.class));
    }
}
