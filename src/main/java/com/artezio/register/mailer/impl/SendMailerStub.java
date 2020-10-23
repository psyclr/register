package com.artezio.register.mailer.impl;

import com.artezio.register.event.publisher.UserEventPublisher;
import com.artezio.register.mailer.SendMailer;
import com.artezio.register.model.dto.MessageStatus;
import com.artezio.register.model.dto.UserDto;
import com.artezio.register.model.event.Message;
import com.artezio.register.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class SendMailerStub implements SendMailer {
    Logger logger = LoggerFactory.getLogger(SendMailerStub.class);
    private final EventService eventService;

    @Override
    public void sendMail(UserDto user, MessageStatus status) throws TimeoutException {
        if (shouldThrowTimeout()) {
            sleep();

            throw new TimeoutException("Timeout!");
        }

        if (shouldSleep()) {
            sleep();
        }

        // ok.
        logger.info("Message sent to {}, body {}.", user.getEmail(), status);
        eventService.send(user, status);
    }

    @SneakyThrows
    private static void sleep() {
        Thread.sleep(TimeUnit.MINUTES.toMillis(1));
    }

    private static boolean shouldSleep() {
        return new Random().nextInt(10) == 1;
    }

    private static boolean shouldThrowTimeout() {
        return new Random().nextInt(10) == 1;
    }
}