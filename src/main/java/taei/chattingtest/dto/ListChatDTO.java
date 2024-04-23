package taei.chattingtest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ListChatDTO {

    private String chatId;
    private String messageType;
    private String message;
    private List<String> images;
    private String status;
    private LocalDateTime createdAt;
    private Long userId;
    private String userProfilePhoto;
    private String userNickName;
    private Long expertId;
    private String expName;
    private String expPhoto;
    private boolean myChat;
    private boolean expert;
}
