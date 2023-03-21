package com.mdas.api.g6.user.domain.services;

import com.mdas.api.g6.user.domain.User;
import com.mdas.api.g6.user.domain.exception.UserNotFoundException;
import com.mdas.api.g6.user.domain.repository.UserRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserFinder {

    private UserRepository userRepository;
    public User getUserById(UUID userId) throws UserNotFoundException {

       User result = userRepository.getUserById(userId);

       if(result == null){
           throw new UserNotFoundException("User not found: " + userId.toString());
       }

       return result;
    }




}
