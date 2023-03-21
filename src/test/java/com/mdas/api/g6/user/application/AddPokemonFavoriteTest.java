package com.mdas.api.g6.user.application;

import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.exception.PokemonAlreadyAddException;
import com.mdas.api.g6.user.domain.exception.UserNotFoundException;
import com.mdas.api.g6.user.domain.services.UserAddPokemonFavorite;
import com.mdas.api.g6.user.domain.services.UserFinder;
import com.mdas.api.g6.user.domain.valueobject.FavoritePokemons;
import com.mdas.api.g6.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.domain.valueobject.UserName;
import com.mdas.api.g6.user.infrastructure.controller.dto.AddFavoritePokemonRequest;
import com.mdas.api.g6.user.objectmother.UserIdMother;
import com.mdas.api.g6.user.objectmother.UserNameMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddPokemonFavoriteTest {

    @Mock
    private UserFinder userFinder;

    @Mock
    private UserAddPokemonFavorite userAddPokemonFavorite;

    @InjectMocks
    private AddPokemonFavoriteUseCase addPokemonFavorite;

    @Test
    void shouldAddPokemonFavoriteSuccessfully() throws PokemonAlreadyAddException, UserNotFoundException {

        // GIVEN
        UserId userId = UserIdMother.random();
        UserName userName = UserNameMother.random();
        User objUser = new User(userId, userName, new FavoritePokemons());

        when(userAddPokemonFavorite.addPokemonFavorite(Mockito.any(User.class), Mockito.any(Integer.class))).thenReturn(objUser);
        when(userFinder.getUserById(Mockito.any(UUID.class))).thenReturn(objUser);
        AddFavoritePokemonRequest request = new AddFavoritePokemonRequest();
        request.setPokemonId(23);

        User result = addPokemonFavorite.execute(userId.getId().toString(), request);

        assertNotNull(result);
        assertNotNull(result.getId());
    }

}
