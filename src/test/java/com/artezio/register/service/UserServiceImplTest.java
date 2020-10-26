package com.artezio.register.service;

import com.artezio.register.service.event.publisher.UserEventPublisher;
import com.artezio.register.dto.MessageStatus;
import com.artezio.register.dto.UserDto;
import com.artezio.register.dto.Message;
import com.artezio.register.service.event.EventService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceImplTest {
    @MockBean
    private UserEventPublisher eventPublisher;
    @Autowired
    private EventService eventService;
    @Captor
    protected ArgumentCaptor<Message> publishEventCaptor;
    @Captor
    protected ArgumentCaptor<MessageStatus> statusArgumentCaptor;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void pushEvent() {
        UserDto user = UserDto.builder().login("login").email("email@email").password("passwordPassword").build();
        MessageStatus status = MessageStatus.NEW;

        doAnswer(invocation -> {
            Message messageValue = publishEventCaptor.getValue();
            assertNotNull(messageValue);
            assertEquals(messageValue.getUser(), user);

            MessageStatus messageStatus = statusArgumentCaptor.getValue();
            assertNotNull(messageStatus);
            assertEquals(messageStatus, status);
            return null;
        }).when(eventPublisher).publishMessageEvent(publishEventCaptor.capture(), statusArgumentCaptor.capture());

        eventService.send(user, status);

        verify(eventPublisher, times(1)).publishMessageEvent(Message.builder().user(user).build(), MessageStatus.NEW);
    }
}