package com.artezio.register.service.messaging;

import com.artezio.register.dto.UserDto;

import javax.validation.constraints.NotNull;

public interface MessagingService {
    void approveAndSend(@NotNull UserDto userDto);
}
