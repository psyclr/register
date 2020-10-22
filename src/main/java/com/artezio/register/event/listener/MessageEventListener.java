package com.artezio.register.event.listener;

import com.artezio.register.mailer.EmailAddress;
import com.artezio.register.mailer.EmailContent;
import com.artezio.register.mailer.SendMailer;
import com.artezio.register.model.dto.UserDto;
import com.artezio.register.model.event.MessageEvent;
import com.artezio.register.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageEventListener {
    SendMailer mailer;
    UserService userService;

    @EventListener
    public void listen(ContextStartedEvent event) {
        MessageEvent source = (MessageEvent) event.getSource();
        String message = source.getMessage();
        UserDto user = userService.getById(UUID.fromString(message));
//        mailer.sendMail(new EmailAddress(user.getEmail()), new EmailContent(""));
        System.out.println(event.getSource().toString());
    }
}
