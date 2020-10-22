package com.artezio.register.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String login;
    private String password;
    private String email;
    private String name;
}
