package taei.chattingtest.dto;

import lombok.Data;

@Data
public class ListMyPrivateChatRoomReqVO {

    private Long userId;
    private int page;
    private int pageSize;
}
