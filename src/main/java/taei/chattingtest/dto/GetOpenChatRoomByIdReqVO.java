package taei.chattingtest.dto;

import lombok.Data;

@Data
public class GetOpenChatRoomByIdReqVO {

    private long roomId;
    private long userId;
}
