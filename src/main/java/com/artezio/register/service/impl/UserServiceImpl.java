package com.artezio.register.service.impl;

import com.artezio.register.event.publisher.UserEventPublisher;
import com.artezio.register.exception.UserAlreadyExistException;
import com.artezio.register.exception.UserNotFoundException;
import com.artezio.register.mapper.UserMapper;
import com.artezio.register.model.dto.MessageStatus;
import com.artezio.register.model.dto.UserDto;
import com.artezio.register.model.entity.User;
import com.artezio.register.repository.UserRepository;
import com.artezio.register.service.EventService;
import com.artezio.register.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EventService eventService;
    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, EventService eventService, UserMapper mapper) {
        this.userRepository = repository;
        this.eventService = eventService;
        this.mapper = mapper;
    }

    @Override
    public UserDto getById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapper.mapToDto(user);
    }

    @Override
    public UserDto saveUser(@NotNull UserDto userDto) {
        User mapped = mapper.mapToEntity(userDto);
        checkExistingUser(userDto);
        User saved = userRepository.save(mapped);
        eventService.send(userDto, MessageStatus.NEW);
        return mapper.mapToDto(saved);
    }

    private void checkExistingUser(@NotNull UserDto userDto) {
        userRepository.findByLogin(userDto.getLogin())
                .ifPresent(user -> {
                    throw new UserAlreadyExistException(user.getLogin());
                });
    }
}
