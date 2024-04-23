package taei.chattingtest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import taei.chattingtest.enums.MessageType;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpertChatDTO {
    // 메시지 타입 : 입장, 퇴장, 채팅
    // 메시지 타입에 따라 동작 구조 달라짐
    // 입장과 퇴장 ENTER, LEAVE경우 입/퇴장 이벤트 처리 실행
    // TALK는 말 그대로 내용이 해당 채팅방을 SUB하고 있는 모든 클라이언트에게 전달 된다.

    private MessageType messageType;
    private Long roomId;
    private Long expertId;
    private String message;
    private String status;
    private List<String> images;
    private LocalDateTime createdAt;
    private String expertName;
    private String expertPhoto;
}
