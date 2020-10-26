package com.artezio.register.messaging;

import com.artezio.register.service.event.publisher.UserEventPublisher;
import com.artezio.register.dto.MessageStatus;
import com.artezio.register.dto.UserDto;
import com.artezio.register.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

import static com.artezio.register.dto.MessageStatus.FAILED;
import static com.artezio.register.dto.MessageStatus.SUCCESSFUL;

@Service
@RequiredArgsConstructor
public class MessagingServiceStub {
    private final UserEventPublisher publisher;

    public void approveAndSend(@NotNull UserDto userDto) {
        MessageStatus status = userDto.getLogin().startsWith("a") ? SUCCESSFUL : FAILED;
        publisher.publishMessageEvent(Message.builder()
                .status(status).user(userDto).build(), MessageStatus.SEND);
    }
}
