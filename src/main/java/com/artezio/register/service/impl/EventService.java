package com.artezio.register.service.impl;

import com.artezio.register.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    public UserDto sendApprove(UserDto userDto) {
        return userDto;
    }
}
