package com.artezio.register.service.listener;

import com.artezio.register.model.dto.Message;

/**
 * Опциональный интерфейс для лисенеров.
 * Необязательно реализовывать всю инфраструктуру по регистрации и обработке, достаточно и тестов.
 *
 * @param <T> тип сообщений, которые будем слушать.
 */
public interface MessageListener<T> {
    void handleMessage(Message<T> incomingMessage);
}
