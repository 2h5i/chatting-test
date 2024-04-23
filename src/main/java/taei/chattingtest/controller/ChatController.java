package taei.chattingtest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import taei.chattingtest.config.HttpHeaderDefaultType;
import taei.chattingtest.dto.*;
import taei.chattingtest.repo.ChatRepository;
import taei.chattingtest.service.ChatRoomService;
import taei.chattingtest.service.ChatService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController {

    private final SimpMessageSendingOperations template;
    private final ChatRepository repository;
    private final ChatRoomService chatRoomService;
    private final ChatService chatService;
    private final HttpHeaderDefaultType httpHeaderDefaultType;

    @MessageMapping("/enterUserOpenChatRoom")
    public void enterUser(@Payload UserEnterReq userChatDTO) {
        log.info("CHAT {}", userChatDTO);
        UserEnterRes userChatRes = chatRoomService.enterUser(userChatDTO);
        template.convertAndSend("/sub/open-chat/room/" + userChatRes.getRoomId(), userChatRes);
    }

    @MessageMapping("/sendUserMessageOpenChatRoom")
    public void sendUserMessage(@Payload UserChatReq userChatReq) {
        log.info("CHAT {}", userChatReq);
        UserChatRes userChatRes = chatService.sendUserMessage(userChatReq);
        template.convertAndSend("/sub/open-chat/room/" + userChatRes.getRoomId(), userChatRes);
    }

    @MessageMapping("/leaveUserOpenChatRoom")
    public void leaveUser(@Payload UserLeaveReq userLeaveReq) {
        log.info("CHAT {}", userLeaveReq);
        UserLeaveRes userLeaveRes = chatRoomService.leaveUser(userLeaveReq);
        template.convertAndSend("/sub/open-chat/room/" + userLeaveRes.getRoomId(), userLeaveRes);
    }

    @MessageMapping("/sendUserMessagePrivateChatRoom")
    public void sendUserMessagePrivateChatRoom(@Payload UserPrivateChatReqVO userPrivateChatReqVO) {
        log.info("CHAT {}", userPrivateChatReqVO);
        UserPrivateChatResVO userPrivateChatResVO = chatService.sendUserPrivateMessage(userPrivateChatReqVO);
        template.convertAndSend("/sub/private-chat/room/" + userPrivateChatResVO.getRoomId(), userPrivateChatResVO);
    }

    @GetMapping("/chat/listOpenChat")
    public ResponseEntity<ListOpenChatResVO> listChat(ListOpenChatReqVO listChatReqVO) {
        ListOpenChatResVO listChatResVO = chatService.listOpenChat(listChatReqVO);
        return new ResponseEntity<>(listChatResVO, httpHeaderDefaultType.getHeader(), HttpStatus.OK);
    }

    @GetMapping("/chat/listPrivateChat")
    public ResponseEntity<ListPrivateChatResVO> listPrivateChat(ListPrivateChatReqVO listPrivateChatReqVO) {
        ListPrivateChatResVO listPrivateChatResVO = chatService.listPrivateChat(listPrivateChatReqVO);
        return new ResponseEntity<>(listPrivateChatResVO, httpHeaderDefaultType.getHeader(), HttpStatus.OK);
    }
}
