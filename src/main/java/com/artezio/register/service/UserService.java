package com.artezio.register.service;

import com.artezio.register.model.dto.UserDto;
import com.artezio.register.model.entity.User;

public interface UserService {
    User saveApproved(UserDto userDto);
}
