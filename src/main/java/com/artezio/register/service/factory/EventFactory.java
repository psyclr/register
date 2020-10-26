package com.artezio.register.service.factory;

import com.artezio.register.dto.AbstractApprovalEvent;
import com.artezio.register.dto.ReceivedEvent;
import com.artezio.register.dto.SendEvent;
import com.artezio.register.dto.MessageStatus;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class EventFactory {
    public AbstractApprovalEvent createEvent(Object source, @NotNull MessageStatus eventStatus) {
        AbstractApprovalEvent event = null;
        switch (eventStatus) {
            case NEW: {
                event = new SendEvent(source);
                break;
            }
            case SEND:
                event = new ReceivedEvent(source);
                break;
        }
        return event;
    }
}
