package com.artezio.register.service.impl;

import com.artezio.register.model.dto.UserDto;
import com.artezio.register.model.entity.User;
import com.artezio.register.repository.UserRepository;
import com.artezio.register.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Override
    public User saveApproved(UserDto userDto) {
        return userRepository.save(new User(userDto));
    }

}
