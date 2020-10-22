package com.artezio.register.mailer;

import java.util.concurrent.TimeoutException;

public interface SendMailer {
    void sendMail(EmailAddress toAddress, EmailContent messageBody) throws TimeoutException;
}
