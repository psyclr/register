package com.artezio.register.event.listener;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class MessageEventListener {

    @EventListener
    public void listen(ContextStartedEvent event){
        System.out.println(event.getSource().toString());
    }
}
