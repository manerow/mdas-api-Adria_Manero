package com.mdas.api.g6.user.user.infrastructure.persistence;

import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.user.infrastructure.persistence.entity.UserInMemoryEntity;
import com.mdas.api.g6.user.user.infrastructure.persistence.mapper.UserInMemoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class UserInMemoryRepository implements UserRepository {

    private final UserInMemoryMapper userInMemoryMapper;

    private final Map<Long, UserInMemoryEntity> userMap = new HashMap<>();

    public boolean existsById(UserId userId) {
        return userMap.containsKey(userId.getId());
    }

    public void save(User user) {
        UserInMemoryEntity userInMemoryEntity = userInMemoryMapper.toEntity(user);
        userMap.put(userInMemoryEntity.getId(), userInMemoryEntity);
    }

    public User getUserById(UserId userId) {
        return userInMemoryMapper.toDomain(userMap.get(userId.getId()));
    }
}
