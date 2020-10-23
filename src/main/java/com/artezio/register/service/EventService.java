package com.artezio.register.service;

import com.artezio.register.model.dto.MessageStatus;
import com.artezio.register.model.dto.UserDto;

public interface EventService {
    void send(UserDto userDto, MessageStatus status);
}
