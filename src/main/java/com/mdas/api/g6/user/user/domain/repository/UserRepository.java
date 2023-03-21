package com.mdas.api.g6.user.user.domain.repository;

import com.mdas.api.g6.user.user.domain.User;

import java.util.UUID;

public interface UserRepository {

    boolean existsByName(String userName);
    void save(User user);
    User getUserById(UUID userId);
    void update(User user);

}
