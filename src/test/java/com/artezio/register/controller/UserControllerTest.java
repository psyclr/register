package com.artezio.register.controller;

import com.artezio.register.dto.UserDto;
import com.artezio.register.service.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String EMAIL_EMAIL_COM = "email@email.com";
    @Autowired
    protected MockMvc mockMvc;
    @MockBean
    protected UserService userService;

    @Autowired
    protected ObjectMapper mapper;

    @Test
    void saveUser() throws Exception {
        UserDto user = UserDto.builder().login(LOGIN).password(PASSWORD).email(EMAIL_EMAIL_COM).build();
        when(userService.saveUser(any(UserDto.class))).thenReturn(user);
        MockHttpServletRequestBuilder content = MockMvcRequestBuilders.post("/users/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user));

        mockMvc.perform(content)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value(LOGIN))
                .andExpect(jsonPath("$.password").value(PASSWORD))
                .andExpect(jsonPath("$.email").value(EMAIL_EMAIL_COM));
    }
}
