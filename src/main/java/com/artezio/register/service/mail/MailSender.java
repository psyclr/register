package com.artezio.register.service.mail;

import com.artezio.register.dto.MessageStatus;
import com.artezio.register.dto.UserDto;

import java.util.concurrent.TimeoutException;

public interface MailSender {
    void sendMail(UserDto user, MessageStatus status) throws TimeoutException;
}
