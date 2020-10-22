package com.artezio.register.model.event;

import org.springframework.context.ApplicationEvent;

public class AbstractApprovalEvent extends ApplicationEvent {

    public AbstractApprovalEvent(Object source) {
        super(source);
    }
}
