package com.artezio.register.event;

import com.artezio.register.model.event.MessageEvent;
import org.springframework.stereotype.Service;

@Service
public class EventFactory {

    public MessageEvent createEvent(Object source, String message) {
        return new MessageEvent(source, message);
    }
}
