package com.artezio.register.service;

import com.artezio.register.event.publisher.UserEventPublisher;
import com.artezio.register.exception.UserAlreadyExistException;
import com.artezio.register.mapper.UserMapper;
import com.artezio.register.model.dto.UserDto;
import com.artezio.register.model.entity.User;
import com.artezio.register.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.UUID;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceImplTest {
    @MockBean
    private UserEventPublisher eventPublisher;
    @Mock
    private UserRepository repository;
    @Autowired
    private UserMapper mapper;
    User user;
    @Captor
    protected ArgumentCaptor<Object> publishEventCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void when_user_valid_then_push_event() {
        doAnswer(new AssignIdToArticleAnswer(UUID.randomUUID())).when(repository).save(any(User.class));

        UserService userService = new UserServiceImpl(repository, eventPublisher, mapper);
        UserDto userDto = UserDto.builder().name("name").login("login").password("pass").email("email").build();
        userService.saveUser(userDto);
        Mockito.verify(eventPublisher).publishMessageEvent(publishEventCaptor.capture());
    }

    @Test
    void when_user_exists_then_exception() {
        UserService userService = new UserServiceImpl(repository, eventPublisher, mapper);
        UserDto user = UserDto.builder()
                .login("login").email("email").password("password").name("name").build();
        userService.saveUser(user);
        Assert.assertThrows(UserAlreadyExistException.class, () -> userService.saveUser(user));
    }

    public class AssignIdToArticleAnswer implements Answer<Void> {

        private final UUID id;

        public AssignIdToArticleAnswer(UUID id) {
            this.id = id;
        }

        @Override
        public Void answer(InvocationOnMock invocation) throws Throwable {
            User user = (User) invocation.getArguments()[0];
            user.setId(id);
            return null;
        }
    }
}