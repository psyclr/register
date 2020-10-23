package com.artezio.register.event.publisher;

import com.artezio.register.event.EventFactory;
import com.artezio.register.model.dto.MessageStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import static com.artezio.register.model.dto.MessageStatus.NEW;

@Component
@RequiredArgsConstructor
public class UserEventPublisher {
    EventFactory eventFactory;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishMessageEvent(final Object message, MessageStatus status) {
        applicationEventPublisher.publishEvent(eventFactory.createEvent(message, status));
    }
}