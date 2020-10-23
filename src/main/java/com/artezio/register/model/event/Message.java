package com.artezio.register.model.event;


import com.artezio.register.model.dto.MessageStatus;
import com.artezio.register.model.dto.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    UserDto user;
    MessageStatus status;
}
