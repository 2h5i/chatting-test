package taei.chattingtest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;
import taei.chattingtest.dto.ChatDTO;
import taei.chattingtest.repo.ChatRepository;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController {

    private final SimpMessageSendingOperations template;
    private final ChatRepository repository;

    @MessageMapping("/enterUser")
    public void enterUser(@Payload ChatDTO chat, SimpMessageHeaderAccessor headerAccessor) {
        log.info("CHAT {}", chat);
        repository.plusUserCnt(chat.getRoomId());
        String userUUID = repository.addUser(chat.getRoomId(), chat.getSenderId());

        headerAccessor.getSessionAttributes().put("userUUID", userUUID);
        headerAccessor.getSessionAttributes().put("roomId", chat.getRoomId());

        chat.setMessage(chat.getSenderId() + "님이 입장했습니다.");
        template.convertAndSend("/sub/chat/room/" + chat.getRoomId(), chat);
    }

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload ChatDTO chatDTO) {
        log.info("CHAT {}", chatDTO);
        chatDTO.setMessage(chatDTO.getMessage());
        template.convertAndSend("/sub/chat/room" + chatDTO.getRoomId(), chatDTO);
    }
}
