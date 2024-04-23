package taei.chattingtest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PrivateChatRoomDTO {
    private Long roomId;
    private String expName;
    private String expPhoto;
    private String lastMsg;
    private LocalDateTime lastMsgTime;
}
