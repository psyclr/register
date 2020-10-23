package com.artezio.register.event.listener;

import com.artezio.register.mailer.impl.SendMailerStub;
import com.artezio.register.messaging.MessagingServiceStub;
import com.artezio.register.model.event.Message;
import com.artezio.register.model.event.ReceivedEvent;
import com.artezio.register.model.event.SendEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageEventListener {
    private final SendMailerStub mailerStub;
    private final MessagingServiceStub messagingStub;

    @EventListener({SendEvent.class})
    public void listenSentEvent(SendEvent sendEvent) {
        Message message = (Message) sendEvent.getSource();
        nullCheck(message);
        log.info("Received new message " + message.toString());
        messagingStub.approveAndSend(message.getUser());
    }

    private void trySendMail(Message message) throws TimeoutException {
        mailerStub.sendMail(message.getUser(), message.getStatus());
    }

    @EventListener({ReceivedEvent.class})
    public void listenReceivedEvent(ReceivedEvent receivedApprovalEvent) {
        Message message = (Message) receivedApprovalEvent.getSource();
        nullCheck(message);
        log.info("Received new message " + message.toString());
        try {
            trySendMail(message);
        } catch (TimeoutException firstEx) {
            try {
                trySendMail(message);
            } catch (TimeoutException secondEx) {
                log.error("Something went wrong {}, please try again later: ", secondEx.getMessage());
            }
        }
    }

    private void nullCheck(Message object) {
        if (object == null) {
            throw new NullPointerException(Message.class.getSimpleName());
        }
    }
}
