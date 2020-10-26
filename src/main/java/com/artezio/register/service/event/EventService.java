package com.artezio.register.service.event;

import com.artezio.register.dto.MessageStatus;
import com.artezio.register.dto.UserDto;

public interface EventService {
    void send(UserDto userDto, MessageStatus status);
}
