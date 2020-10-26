package com.artezio.register.service.event.publisher;

import com.artezio.register.service.factory.EventFactory;
import com.artezio.register.dto.MessageStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventPublisher {
    EventFactory eventFactory;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishMessageEvent(final Object message, MessageStatus status) {
        applicationEventPublisher.publishEvent(eventFactory.createEvent(message, status));
    }
}