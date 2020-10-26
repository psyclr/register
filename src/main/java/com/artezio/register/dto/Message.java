package com.artezio.register.dto;


import com.artezio.register.dto.MessageStatus;
import com.artezio.register.dto.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    UserDto user;
    MessageStatus status;
}
