package taei.chattingtest.dto;

import lombok.Data;
import taei.chattingtest.enums.MessageType;

import java.time.LocalDateTime;

@Data
public class UserChatRes {

    private String id;
    private MessageType messageType;
    private Long roomId;
    private Long userId;
    private String message;
    private String status;
    private LocalDateTime createdAt;
    private String userNickName;
    private String userPhoto;

    private UserChatRes(UserChat userChat, UserChatReq userChatReq) {
        this.id = userChat.getId();
        this.messageType = userChat.getMessageType();
        this.roomId = userChatReq.getRoomId();
        this.userId = userChatReq.getUserId();
        this.message = userChat.getMessage();
        this.status = userChat.getStatus();
        this.createdAt = userChat.getCreatedAt();
        this.userNickName = userChatReq.getUserNickName();
        this.userPhoto = userChatReq.getUserPhoto();
    }

    public static UserChatRes of(UserChat userChat, UserChatReq userChatReq) {
        return new UserChatRes(userChat, userChatReq);
    }
}
