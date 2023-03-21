package com.mdas.api.g6.user.infrastructure.persistence.repository;

import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.infrastructure.persistence.entity.UserInMemoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
}
