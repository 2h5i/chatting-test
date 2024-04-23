package taei.chattingtest.dto;

import lombok.Data;

@Data
public class ListPrivateChatReqVO {

    private long roomId;
    private long userId;
    private int page;
    private int pageSize;
}
