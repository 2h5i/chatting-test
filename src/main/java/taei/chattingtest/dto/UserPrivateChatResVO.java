package taei.chattingtest.dto;

import lombok.Getter;
import taei.chattingtest.enums.MessageType;

import java.time.LocalDateTime;

@Getter
public class UserPrivateChatResVO {

    private String id;
    private MessageType messageType;
    private Long roomId;
    private Long userId;
    private String message;
    private String status;
    private LocalDateTime createdAt;
    private String userNickName;
    private String userPhoto;

    private UserPrivateChatResVO(PrivateUserChat privateUserChat, UserPrivateChatReqVO userPrivateChatReqVO) {
        this.id = privateUserChat.getId();
        this.messageType = privateUserChat.getMessageType();
        this.roomId = userPrivateChatReqVO.getRoomId();
        this.userId = userPrivateChatReqVO.getUserId();
        this.message = privateUserChat.getMessage();
        this.status = privateUserChat.getStatus();
        this.createdAt = privateUserChat.getCreatedAt();
        this.userNickName = userPrivateChatReqVO.getUserNickName();
        this.userPhoto = userPrivateChatReqVO.getUserPhoto();
    }

    public static UserPrivateChatResVO of(PrivateUserChat privateUserChat, UserPrivateChatReqVO userPrivateChatReqVO) {
        return new UserPrivateChatResVO(privateUserChat, userPrivateChatReqVO);
    }
}
