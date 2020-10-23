package com.artezio.register.messaging;

import com.artezio.register.event.publisher.UserEventPublisher;
import com.artezio.register.model.dto.MessageStatus;
import com.artezio.register.model.dto.UserDto;
import com.artezio.register.model.event.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

import static com.artezio.register.model.dto.MessageStatus.FAILED;
import static com.artezio.register.model.dto.MessageStatus.SUCCESSFUL;

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
