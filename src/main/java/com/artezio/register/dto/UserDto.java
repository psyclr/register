package com.artezio.register.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Builder
public class UserDto {
    private UUID id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @Email
    @NotBlank
    private String email;
    private String name;

}
