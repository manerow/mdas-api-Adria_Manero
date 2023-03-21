package com.mdas.api.g6.user.user.infrastructure.persistence.adapter;

import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.user.infrastructure.persistence.entity.UserInMemoryEntity;
import com.mdas.api.g6.user.user.infrastructure.persistence.mapper.UserInMemoryMapper;
import com.mdas.api.g6.user.user.infrastructure.persistence.repository.UserInMemoryRepository;
import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserInMemoryAdapter implements UserRepository {

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

    @Override
    public User getUserById(UUID userId) {

        Optional<UserInMemoryEntity> po = userInMemoryRepository.getUserById(userId);

        return  po.isPresent() ? userInMemoryMapper.toDomain(po.get()) : null ;
    }

    @Override
    public void update(User user) {
        UserInMemoryEntity userInMemoryEntity = userInMemoryMapper.toEntity(user);
        userInMemoryRepository.update(userInMemoryEntity);
    }

}
