package taei.chattingtest.dto;

import lombok.Data;
import taei.chattingtest.enums.MessageType;

import java.time.LocalDateTime;

@Data
public class UserLeaveRes {

    private String id;
    private MessageType messageType;
    private Long roomId;
    private Long userId;
    private String message;
    private String status;
    private LocalDateTime createdAt;
    private String userNickName;
    private String userPhoto;

    private UserLeaveRes(UserChat userChat, UserLeaveReq userLeaveReq) {
        this.id = userChat.getId();
        this.messageType = userChat.getMessageType();
        this.roomId = userLeaveReq.getRoomId();
        this.userId = userLeaveReq.getUserId();
        this.message = userChat.getMessage();
        this.status = userChat.getStatus();
        this.createdAt = userChat.getCreatedAt();
        this.userNickName = userLeaveReq.getUserNickName();
        this.userPhoto = userLeaveReq.getUserPhoto();
    }

    public static UserLeaveRes of(UserChat userChat, UserLeaveReq userLeaveReq) {
        return new UserLeaveRes(userChat, userLeaveReq);
    }
}
