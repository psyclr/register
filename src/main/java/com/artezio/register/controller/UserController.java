package com.artezio.register.controller;

import com.artezio.register.model.dto.UserDto;
import com.artezio.register.service.impl.EventService;
import com.artezio.register.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EventService eventService;

    @PostMapping("/save")
    public ResponseEntity<UserDto> saveUser(UserDto user){
        return  ResponseEntity.ok(userService.saveUser(user));
    }
}
