package com.artezio.register.service.event;

import com.artezio.register.service.event.publisher.UserEventPublisher;
import com.artezio.register.dto.MessageStatus;
import com.artezio.register.dto.UserDto;
import com.artezio.register.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageEventService implements EventService {
    private final UserEventPublisher publisher;

    @Override
    public void send(UserDto userDto, MessageStatus status) {
        publisher.publishMessageEvent(Message.builder().user(userDto).build(), status);
    }
}
