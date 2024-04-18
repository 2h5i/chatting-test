package taei.chattingtest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taei.chattingtest.config.HttpHeaderDefaultType;
import taei.chattingtest.dto.ChatRoom;
import taei.chattingtest.repo.ChatRepository;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chatroom")
public class ChatRoomController {

    private final ChatRepository chatRepository;
    private final HttpHeaderDefaultType httpHeaderDefaultType;

    @GetMapping("/")
    public ResponseEntity<List<ChatRoom>> getChatRoom() {
        List<ChatRoom> chatRooms = chatRepository.findAllRoom();
        return new ResponseEntity<>(chatRooms, httpHeaderDefaultType.getHeader(), HttpStatus.OK);
    }

    @PostMapping("/room")
    public ResponseEntity<String> createRoom(@RequestParam String name, @RequestParam Long ownerId) {
        ChatRoom room = chatRepository.createChatRoom(name, ownerId);
        return new ResponseEntity<>(room.getRoomId(), httpHeaderDefaultType.getHeader(), HttpStatus.OK);
    }

    @GetMapping("/userList")
    public ResponseEntity<List<String>> userList(String roomId) {
        return new ResponseEntity<>(chatRepository.getUserList(roomId), httpHeaderDefaultType.getHeader(), HttpStatus.OK);
    }
}
