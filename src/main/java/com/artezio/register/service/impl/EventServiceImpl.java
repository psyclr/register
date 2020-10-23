package com.artezio.register.service.impl;

import com.artezio.register.event.publisher.UserEventPublisher;
import com.artezio.register.model.dto.MessageStatus;
import com.artezio.register.model.dto.UserDto;
import com.artezio.register.model.event.Message;
import com.artezio.register.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final UserEventPublisher publisher;

    @Override
    public void send(UserDto userDto, MessageStatus status) {
        publisher.publishMessageEvent(Message.builder().user(userDto).build(), status);
    }
}
