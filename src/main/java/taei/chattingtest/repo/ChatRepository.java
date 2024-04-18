package taei.chattingtest.repo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import taei.chattingtest.dto.ChatRoom;

import java.util.*;

@Repository
@Slf4j
public class ChatRepository {

    private Map<String, ChatRoom> chatRoomMap = new LinkedHashMap<>();

    public List<ChatRoom> findAllRoom() {
        List chatRooms = new ArrayList(chatRoomMap.values());
        Collections.reverse(chatRooms);

        return chatRooms;
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRoomMap.get(roomId);
    }

    public ChatRoom createChatRoom(String roomName, Long roomOwnerId) {
        ChatRoom chatRoom = new ChatRoom().create(roomName, roomOwnerId);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);

        return chatRoom;
    }

    public void plusUserCnt(String roomId) {
        ChatRoom room = chatRoomMap.get(roomId);
        room.setUserCount(room.getUserCount() + 1);
    }

    public void minusUserCnt(String roomId) {
        ChatRoom room = chatRoomMap.get(roomId);
        room.setUserCount(room.getUserCount() - 1);
    }

    public String addUser(String roomId, String userName) {
        ChatRoom room = chatRoomMap.get(roomId);
        String userUUID = UUID.randomUUID().toString();

        room.getUserList().put(userUUID, userName);

        return userUUID;
    }

    public void delUser(String roomId, String userUUID) {
        ChatRoom room = chatRoomMap.get(roomId);
        room.getUserList().remove(userUUID);
    }

    public String getUserName(String roomId, String userUUID) {
        ChatRoom room = chatRoomMap.get(roomId);
        return room.getUserList().get(userUUID);
    }

    public ArrayList<String> getUserList(String roomId) {
        ArrayList<String> list = new ArrayList<>();
        ChatRoom room = chatRoomMap.get(roomId);

        room.getUserList().forEach((key, value) -> list.add(value));
        return list;
    }
}
