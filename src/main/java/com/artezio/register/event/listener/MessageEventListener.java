package com.artezio.register.event.listener;

import com.artezio.register.model.event.ReceivedEvent;
import com.artezio.register.model.event.SendEvent;
import com.artezio.register.mailer.EmailAddress;
import com.artezio.register.mailer.EmailContent;
import com.artezio.register.mailer.SendMailer;
import com.artezio.register.model.dto.UserDto;
import com.artezio.register.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageEventListener {
    SendMailer mailer;
    UserService userService;

    @EventListener
    public void listenSentApprovalEvent(SendEvent sendEvent) {
        log.info("STARTED TO LISTEN  :" + sendEvent.getSource().toString() + " ");
    }

    @EventListener
    public void listenReceivedEvent(ReceivedEvent receivedEvent) {
        UserDto user = userService.getById(UUID.fromString(receivedEvent.getSource().toString()));

        try {
            mailer.sendMail(new EmailAddress(user.getEmail()), new EmailContent(""));
        } catch (TimeoutException e) {
            log.error("There was an exceptional situation: {}, try again later: ", e.getMessage());
        }
    }

}
