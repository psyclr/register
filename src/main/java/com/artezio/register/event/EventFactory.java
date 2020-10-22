package com.artezio.register.event;

import com.artezio.register.model.event.AbstractApprovalEvent;
import com.artezio.register.model.event.ReceivedEvent;
import com.artezio.register.model.event.SendEvent;
import com.artezio.register.model.dto.MessageStatus;
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
