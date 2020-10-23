package com.artezio.register.mailer;

import com.artezio.register.model.dto.MessageStatus;
import com.artezio.register.model.dto.UserDto;

import java.util.concurrent.TimeoutException;

public interface SendMailer {
    void sendMail(UserDto user, MessageStatus status) throws TimeoutException;
}
