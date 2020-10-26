package com.artezio.register.dto;

import org.springframework.context.ApplicationEvent;

public class AbstractApprovalEvent extends ApplicationEvent {

    public AbstractApprovalEvent(Object source) {
        super(source);
    }
}
