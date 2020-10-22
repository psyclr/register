package com.artezio.register.service.impl;

import com.artezio.register.event.publisher.UserEventPublisher;
import com.artezio.register.model.dto.UserDto;
import com.artezio.register.repository.UserRepository;
import com.artezio.register.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceImplTest {
    @MockBean
    private UserEventPublisher eventPublisher;
    @MockBean
    private UserRepository repository;
    @Captor
    protected ArgumentCaptor<Object> publishEventCaptor;
    private UserService userService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void saveApproved() {
        userService = new UserServiceImpl(repository, eventPublisher);
        userService.saveUser(UserDto.builder()
                .login("login").email("email").password("password").name("name").build());
        Mockito.verify(eventPublisher).publishMessageEvent(publishEventCaptor.capture());

        List<Object> capturedEvents = publishEventCaptor.getAllValues();
        Object o = capturedEvents.get(0);
    }
}