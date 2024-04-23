package taei.chattingtest.dto;

import lombok.Data;

@Data
public class SetChatRoomNotificationReqVO {

    private Long roomId;
    private Long userId;
    private boolean useNotification;
    private String clientId;
}
