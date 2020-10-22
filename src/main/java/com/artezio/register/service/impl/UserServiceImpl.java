package com.artezio.register.service.impl;

import com.artezio.register.event.publisher.UserEventPublisher;
import com.artezio.register.model.dto.UserDto;
import com.artezio.register.model.entity.User;
import com.artezio.register.repository.UserRepository;
import com.artezio.register.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserEventPublisher userEventPublisher;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserEventPublisher eventPublisher) {
        userRepository = repository;
        this.userEventPublisher = eventPublisher;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        userEventPublisher.publishMessageEvent(userDto);
        userRepository.save(new User(userDto));
        return userDto;
    }

}
