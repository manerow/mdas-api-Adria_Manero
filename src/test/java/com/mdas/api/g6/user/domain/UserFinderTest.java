package com.mdas.api.g6.user.domain;

import com.mdas.api.g6.user.domain.exception.UserAlreadyExistsException;
import com.mdas.api.g6.user.domain.exception.UserNotFoundException;
import com.mdas.api.g6.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.domain.services.UserCreator;
import com.mdas.api.g6.user.domain.services.UserFinder;
import com.mdas.api.g6.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.domain.valueobject.UserName;
import com.mdas.api.g6.user.infrastructure.persistence.entity.UserInMemoryEntity;
import com.mdas.api.g6.user.infrastructure.persistence.mapper.UserInMemoryMapper;
import com.mdas.api.g6.user.objectmother.UserIdMother;
import com.mdas.api.g6.user.objectmother.UserNameMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
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

        when(userRepositoryMock.getUserById(Mockito.any(UUID.class))).thenReturn(Optional.of(obj));
        when(userInMemoryMapper.toDomain(Mockito.any(UserInMemoryEntity.class))).thenReturn(objUser);

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
        when(userRepositoryMock.getUserById(Mockito.any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userFinder.getUserById(userId.getId()));

    }
}
