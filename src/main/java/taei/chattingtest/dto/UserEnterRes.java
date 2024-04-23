package taei.chattingtest.dto;

import lombok.Data;
import taei.chattingtest.enums.MessageType;

import java.time.LocalDateTime;

@Data
public class UserEnterRes {

    private String id;
    private MessageType messageType;
    private Long roomId;
    private Long userId;
    private String message;
    private String status;
    private LocalDateTime createdAt;
    private String userNickName;
    private String userPhoto;

    private UserEnterRes(UserChat userChat, UserEnterReq userChatDTO) {
        this.id = userChat.getId();
        this.messageType = userChat.getMessageType();
        this.roomId = userChatDTO.getRoomId();
        this.userId = userChatDTO.getUserId();
        this.message = userChat.getMessage();
        this.status = userChat.getStatus();
        this.createdAt = userChat.getCreatedAt();
        this.userNickName = userChatDTO.getUserNickName();
        this.userPhoto = userChatDTO.getUserPhoto();
    }

    public static UserEnterRes of(UserChat userChat, UserEnterReq userChatDTO) {
        return new UserEnterRes(userChat, userChatDTO);
    }
}
