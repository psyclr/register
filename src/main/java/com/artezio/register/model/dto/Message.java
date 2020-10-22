package com.artezio.register.model.dto;

import lombok.Data;

@Data
public class Message<T> {
    T message;
    MessageStatus status;
}
