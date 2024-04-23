package taei.chattingtest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taei.chattingtest.config.HttpHeaderDefaultType;
import taei.chattingtest.dto.*;
import taei.chattingtest.repo.ChatRepository;
import taei.chattingtest.service.ChatRoomService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chatroom")
public class ChatRoomController {

    private final ChatRepository chatRepository;
    private final HttpHeaderDefaultType httpHeaderDefaultType;
    private final ChatRoomService chatRoomService;

    @GetMapping("/listMyOpenChatRoom")
    public ResponseEntity<ListMyOpenChatRoomResVO> listMyOpenChatRoom(ListMyOpenChatRoomReqVO listMyOpenChatRoomReqVO) {
        ListMyOpenChatRoomResVO listMyOpenChatRoomResVO = chatRoomService.listMyOpenChatRoom(listMyOpenChatRoomReqVO);
        return new ResponseEntity<>(listMyOpenChatRoomResVO, httpHeaderDefaultType.getHeader(), HttpStatus.OK);
    }

    @PostMapping("/createOpenChatRoom")
    public ResponseEntity<Long> createRoom(@RequestParam String name,
                                           @RequestParam Long ownerId,
                                           @RequestParam String photo,
                                           @RequestParam String limitCnt,
                                           @RequestParam String roomType) {
        Long roomId = chatRoomService.createRoom(name, ownerId, photo, limitCnt, roomType);
        return new ResponseEntity<>(roomId, httpHeaderDefaultType.getHeader(), HttpStatus.OK);
    }

    @GetMapping("/listChatRoomUser")
    public ResponseEntity<ListChatRoomUserResVO> listChatRoomUser(ListChatRoomUserReqVO listChatRoomUserReqVO) {
        ListChatRoomUserResVO listChatRoomUserResVO = chatRoomService.listChatRoomUser(listChatRoomUserReqVO);
        return new ResponseEntity<>(listChatRoomUserResVO, httpHeaderDefaultType.getHeader(), HttpStatus.OK);
    }

    @GetMapping("/listAllOpenChatRoom")
    public ResponseEntity<ListAllOpenChatRoomResVO>listAllOpenChatRoom(ListAllOpenChatRoomReqVO listAllOpenChatRoomReqVO) {
        ListAllOpenChatRoomResVO listAllOpenChatRoomResVO = chatRoomService.listAllOpenChatRoom(listAllOpenChatRoomReqVO);
        return new ResponseEntity<>(listAllOpenChatRoomResVO, httpHeaderDefaultType.getHeader(), HttpStatus.OK);
    }

    @PostMapping("/createPrivateChatRoom")
    public ResponseEntity<Long> createPrivateChatRoom(@RequestParam Long expertId,
                                                      @RequestParam Long userId) {
        Long roomId = chatRoomService.createPrivateChatRoom(expertId, userId);
        return new ResponseEntity<>(roomId, httpHeaderDefaultType.getHeader(), HttpStatus.OK);
    }

    @GetMapping("/getMyExpertChatRoom")
    public ResponseEntity<GetMyExpertChatRoomResVO> getMyExpertChatRoom(GetMyExpertChatRoomReqVO getMyExpertChatRoomReqVO) {
        GetMyExpertChatRoomResVO getMyExpertChatRoomResVO = chatRoomService.getMyExpertChatRoom(getMyExpertChatRoomReqVO);
        return new ResponseEntity<>(getMyExpertChatRoomResVO, httpHeaderDefaultType.getHeader(), HttpStatus.OK);
    }

    @GetMapping("/listMyPrivateChatRoom")
    public ResponseEntity<ListMyPrivateChatRoomResVO> listMyPrivateChatRoom(ListMyPrivateChatRoomReqVO listMyPrivateChatRoomReqVO) {
        ListMyPrivateChatRoomResVO listMyPrivateChatRoomResVO = chatRoomService.listMyPrivateChatRoom(listMyPrivateChatRoomReqVO);
        return new ResponseEntity<>(listMyPrivateChatRoomResVO, httpHeaderDefaultType.getHeader(), HttpStatus.OK);
    }

    @GetMapping("/getOpenChatRoomById")
    public ResponseEntity<GetOpenChatRoomByIdResVO> getOpenChatRoomById(GetOpenChatRoomByIdReqVO getOpenChatRoomByIdReqVO) {
        GetOpenChatRoomByIdResVO getOpenChatRoomByIdResVO = chatRoomService.getOpenChatRoomById(getOpenChatRoomByIdReqVO);
        return new ResponseEntity<>(getOpenChatRoomByIdResVO, httpHeaderDefaultType.getHeader(), HttpStatus.OK);
    }

    @PostMapping("/setChatRoomNotification")
    public ResponseEntity<SetChatRoomNotificationResVO> setChatRoomNotification(@RequestBody SetChatRoomNotificationReqVO setChatRoomNotificationReqVO) {
        SetChatRoomNotificationResVO setChatRoomNotificationResVO = chatRoomService.setChatRoomNotification(setChatRoomNotificationReqVO);
        return new ResponseEntity<>(setChatRoomNotificationResVO, httpHeaderDefaultType.getHeader(), HttpStatus.OK);
    }
}
