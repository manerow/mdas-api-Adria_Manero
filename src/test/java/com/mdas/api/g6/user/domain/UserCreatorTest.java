package com.mdas.api.g6.user.domain;

import com.mdas.api.g6.user.domain.exception.UserAlreadyExistsException;
import com.mdas.api.g6.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.domain.services.UserCreator;
import com.mdas.api.g6.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.domain.valueobject.UserName;
import com.mdas.api.g6.user.objectmother.UserIdMother;
import com.mdas.api.g6.user.objectmother.UserMother;
import com.mdas.api.g6.user.objectmother.UserNameMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserCreatorTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserCreator userCreator;

    @Test
    void shouldCreateUserSuccessfully() throws UserAlreadyExistsException {
        // GIVEN
        UserId userId = UserIdMother.random();
        UserName userName = UserNameMother.random();
        when(userRepositoryMock.existsByName(userName.getName())).thenReturn(false);

        // WHEN
        User createdUser = userCreator.create(userId, userName);

        // THEN
        assertEquals(createdUser.getId(), userId);
        assertEquals(createdUser.getName(), userName);
        assertNull(createdUser.getFavouritePokemons());
        verify(userRepositoryMock, times(1)).existsByName(userName.getName());
        verify(userRepositoryMock, times(1)).save(Mockito.any(User.class));
    }

    @Test
    void shouldThrowUserAlreadyExistsExceptionWhenCreatingUserWithExistingName() {
        // GIVEN
        UserId userId = UserIdMother.random();
        UserName userName = UserNameMother.random();
        when(userRepositoryMock.existsByName(userName.getName())).thenReturn(true);

        // WHEN / THEN
        assertThrows(UserAlreadyExistsException.class, () -> userCreator.create(userId, userName));
        verify(userRepositoryMock, times(1)).existsByName(userName.getName());
        verify(userRepositoryMock, never()).save(any(User.class));
    }
}
