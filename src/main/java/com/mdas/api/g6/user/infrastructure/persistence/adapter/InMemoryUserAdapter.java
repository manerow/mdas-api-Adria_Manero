package com.mdas.api.g6.user.infrastructure.persistence.adapter;

import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.infrastructure.persistence.entity.UserInMemoryEntity;
import com.mdas.api.g6.user.infrastructure.persistence.mapper.UserInMemoryMapper;
import com.mdas.api.g6.user.infrastructure.persistence.repository.UserInMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InMemoryUserAdapter implements UserRepository {

    private final UserInMemoryRepository userInMemoryRepository;
    private final UserInMemoryMapper userInMemoryMapper;

    @Override
    public boolean existsByName(String userName) {
        return userInMemoryRepository.existsByName(userName);
    }

    @Override
    public void save(User user) {
        UserInMemoryEntity userInMemoryEntity = userInMemoryMapper.toEntity(user);
        userInMemoryRepository.save(userInMemoryEntity);
    }
}
