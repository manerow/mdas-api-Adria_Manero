package com.mdas.api.g6.user.domain;

import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.domain.exception.UserAlreadyExistsException;
import com.mdas.api.g6.user.user.domain.exception.UserNotFoundException;
import com.mdas.api.g6.user.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.user.domain.services.UserCreator;
import com.mdas.api.g6.user.user.domain.services.UserFinder;
import com.mdas.api.g6.user.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.user.domain.valueobject.UserName;
import com.mdas.api.g6.user.user.infrastructure.persistence.entity.UserInMemoryEntity;
import com.mdas.api.g6.user.user.infrastructure.persistence.mapper.UserInMemoryMapper;
import com.mdas.api.g6.user.objectmother.UserIdMother;
import com.mdas.api.g6.user.objectmother.UserNameMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserFinderTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private UserInMemoryMapper userInMemoryMapper;

    @InjectMocks
    private UserFinder userFinder;

    @InjectMocks
    private UserCreator userCreator;

    @Test
    void shouldUserFinderSuccessfully() throws UserNotFoundException, UserAlreadyExistsException {
        // GIVEN
        UserId userId = UserIdMother.random();
        UserName userName = UserNameMother.random();
        when(userRepositoryMock.existsByName(userName.getName())).thenReturn(false);

        User objUser = userCreator.create(userId, userName);

        UserInMemoryEntity obj = new UserInMemoryEntity();
        obj.setId(objUser.getId().getId());
        obj.setName(objUser.getName().getName());

        when(userRepositoryMock.getUserById(Mockito.any(UUID.class))).thenReturn(objUser);

        // WHEN
        User objFinder = userFinder.getUserById(objUser.getId().getId());

        assertEquals(objFinder.getId(), userId);
        assertEquals(objFinder.getName(), userName);
        assertTrue(objFinder.getFavoritePokemons().getFavoritePokemons().isEmpty());

    }

    @Test
    void shouldThrowUserNotFound() {

        // GIVEN
        UserId userId = UserIdMother.random();

        // WHEN
        when(userRepositoryMock.getUserById(Mockito.any(UUID.class))).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> userFinder.getUserById(userId.getId()));

    }
}
