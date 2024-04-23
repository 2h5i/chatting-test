package taei.chattingtest.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserPrivateChatReqVO {

    private Long roomId;
    private Long userId;
    private String message;
    private List<String> images;
    private String userNickName;
    private String userPhoto;
}
