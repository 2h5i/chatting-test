package taei.chattingtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChatReq {

    private Long roomId;
    private Long userId;
    private String message;
    private List<String> images;
    private String userNickName;
    private String userPhoto;
}
