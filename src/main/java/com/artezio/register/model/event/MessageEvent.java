package com.artezio.register.model.event;

import com.artezio.register.model.dto.Message;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import static com.artezio.register.model.dto.MessageStatus.NEW;

@Getter
public class MessageEvent extends ApplicationEvent {
    private final String message;

    public MessageEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
}