package taei.chattingtest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OpenChatRoomDTO {

    private Long id;
    private String name;
    private String roomType;
    private String photo;
    private int currentUserCnt;
    private int limitUserCnt;
    private LocalDateTime lastMsgTime;
    private boolean useNotification;
    private String status;
}
