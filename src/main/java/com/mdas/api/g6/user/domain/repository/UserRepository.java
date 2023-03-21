package com.mdas.api.g6.user.domain.repository;

import com.mdas.api.g6.user.domain.User;

public interface UserRepository {
    boolean existsByName(String userName);
    void save(User user);
}
