package com.artezio.register.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    String message;
    MessageStatus status;
}
