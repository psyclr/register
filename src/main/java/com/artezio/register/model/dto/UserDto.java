package com.artezio.register.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UserDto {
    private Long id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @Email
    @NotBlank
    private String email;
    private String name;

}
