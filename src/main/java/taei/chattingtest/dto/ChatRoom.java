package taei.chattingtest.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.UUID;

// Stomp를 통해 pub/sub 사용 시 구독자 관리가 알아서 된다.
@Data
public class ChatRoom {
    private String roomId;
    private String roomName;
    private long roomOwnerId;
    private long userCount;
    private HashMap<String, String> userList = new HashMap<String, String>();

    public ChatRoom create(String roomName, long roomOwnerId) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.roomName = roomName;
        chatRoom.roomOwnerId = roomOwnerId;

        return chatRoom;
    }
}
