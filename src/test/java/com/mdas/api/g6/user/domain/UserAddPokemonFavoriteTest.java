package com.mdas.api.g6.user.domain;

import com.mdas.api.g6.user.domain.exception.PokemonAlreadyAddException;
import com.mdas.api.g6.user.domain.exception.UserAlreadyExistsException;
import com.mdas.api.g6.user.domain.exception.UserNotFoundException;
import com.mdas.api.g6.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.domain.services.UserAddPokemonFavorite;
import com.mdas.api.g6.user.domain.services.UserCreator;
import com.mdas.api.g6.user.domain.valueobject.FavoritePokemon;
import com.mdas.api.g6.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.domain.valueobject.UserName;
import com.mdas.api.g6.user.objectmother.UserIdMother;
import com.mdas.api.g6.user.objectmother.UserNameMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAddPokemonFavoriteTest {

    @Mock
    private UserRepository userRepositoryMock;

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
        when(userRepositoryMock.existsByName(userName.getName())).thenReturn(false);

        User objUser = userCreator.create(userId, userName);
        objUser.getFavoritePokemons().getFavoritePokemons().add(new FavoritePokemon(23));

        User result = userAddPokemonFavorite.addPokemonFavorite(objUser,26);

        assertTrue(result.getFavoritePokemons().getFavoritePokemons().contains(new FavoritePokemon(26)));
    }

    @Test
    void shouldThrowPokemonAlreadyAdd() throws UserAlreadyExistsException {

        // GIVEN
        UserId userId = UserIdMother.random();
        UserName userName = UserNameMother.random();

        // WHEN
        when(userRepositoryMock.existsByName(userName.getName())).thenReturn(false);

        User objUser = userCreator.create(userId, userName);
        objUser.getFavoritePokemons().getFavoritePokemons().add(new FavoritePokemon(23));

        assertThrows(PokemonAlreadyAddException.class, () -> userAddPokemonFavorite.addPokemonFavorite(objUser,23));
    }

}
