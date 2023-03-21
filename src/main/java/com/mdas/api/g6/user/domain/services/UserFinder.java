package com.mdas.api.g6.user.domain.services;

import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.exception.UserNotFoundException;
import com.mdas.api.g6.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.infrastructure.persistence.entity.UserInMemoryEntity;
import com.mdas.api.g6.user.infrastructure.persistence.mapper.UserInMemoryMapper;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserFinder {

    private UserRepository userRepository;

    private final UserInMemoryMapper userInMemoryMapper;

    public User getUserById(UUID userId) throws UserNotFoundException {

       Optional<UserInMemoryEntity> op = userRepository.getUserById(userId);

       if(!op.isPresent()){
           throw new UserNotFoundException("User not found: " + userId.toString());
       }

       return userInMemoryMapper.toDomain(op.get());
    }




}
