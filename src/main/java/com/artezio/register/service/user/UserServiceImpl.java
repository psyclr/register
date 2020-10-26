package com.artezio.register.service.user;

import com.artezio.register.exception.UserAlreadyExistException;
import com.artezio.register.exception.UserNotFoundException;
import com.artezio.register.mapper.UserMapper;
import com.artezio.register.dto.MessageStatus;
import com.artezio.register.dto.UserDto;
import com.artezio.register.entity.User;
import com.artezio.register.repository.UserRepository;
import com.artezio.register.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public UserDto getById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapper.mapToDto(user);
    }

    @Override
    @Transactional
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
