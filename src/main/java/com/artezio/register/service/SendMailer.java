package com.artezio.register.service;

/**
 * Ориентировочный интерфейс мейлера.
 */
public interface SendMailer {
    void sendMail (EmailAddress toAddress, EmailContent messageBody) throws TimeoutException;
}
