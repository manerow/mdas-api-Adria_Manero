package com.mdas.api.g6.user.domain.repository;

import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.infrastructure.persistence.entity.UserInMemoryEntity;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository {

    boolean existsByName(String userName);
    void save(User user);
    Optional<UserInMemoryEntity> getUserById(UUID userId);
    void update(User user);

}
