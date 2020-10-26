package com.artezio.register.service.user;

import com.artezio.register.dto.UserDto;

import java.util.UUID;

public interface UserService {
    UserDto saveUser(UserDto userDto);
    UserDto getById(UUID id);
}
