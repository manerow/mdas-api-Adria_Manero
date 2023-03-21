package com.mdas.api.g6.user.infrastructure.persistence.repository;

import com.mdas.api.g6.user.infrastructure.persistence.entity.UserInMemoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class UserInMemoryRepository {

    private final Map<UUID, UserInMemoryEntity> userMap = new HashMap<>();

    public boolean existsByName(String userName) {
        return userMap.values().stream()
                .anyMatch(user -> user.getName().equals(userName));
    }

    public void save(UserInMemoryEntity userInMemoryEntity) {
        userMap.put(userInMemoryEntity.getId(), userInMemoryEntity);
    }

    public Optional<UserInMemoryEntity> getUserById(UUID userId) {
        return userMap.values().stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
    }

    public void update(UserInMemoryEntity user) {
        userMap.put(user.getId(), user);
    }

}
