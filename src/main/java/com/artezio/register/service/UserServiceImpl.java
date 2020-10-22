package com.artezio.register.service;

import com.artezio.register.event.publisher.UserEventPublisher;
import com.artezio.register.exception.UserAlreadyExistException;
import com.artezio.register.exception.UserNotFoundException;
import com.artezio.register.mapper.UserMapper;
import com.artezio.register.model.dto.UserDto;
import com.artezio.register.model.entity.User;
import com.artezio.register.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserEventPublisher userEventPublisher;
    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserEventPublisher eventPublisher, UserMapper mapper) {
        this.userRepository = repository;
        this.userEventPublisher = eventPublisher;
        this.mapper = mapper;
    }

    @Override
    public UserDto getById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapper.mapToDto(user);
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User mapped = mapper.mapToEntity(userDto);
        userRepository.findByLogin(userDto.getLogin())
                .ifPresent(user -> {
                    String errorMessage = "User with login" + userDto.getLogin() + " already exists";
                    throw new UserAlreadyExistException(errorMessage);
                });
        User saved = userRepository.save(mapped);
        userEventPublisher.publishMessageEvent(saved.getId());
        return userDto;
    }
}
