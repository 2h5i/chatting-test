package taei.chattingtest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
    // 메시지 타입 : 입장, 퇴장, 채팅
    // 메시지 타입에 따라 동작 구조 달라짐
    // 입장과 퇴장 ENTER, LEAVE경우 입/퇴장 이벤트 처리 실행
    // TALK는 말 그대로 내용이 해당 채팅방을 SUB하고 있는 모든 클라이언트에게 전달 된다.
    public enum MessageType {
        ENTER, TALK, LEAVE;
    }

    private MessageType messageType;
    private String roomId;
    private String senderId;
    private String message;
    private String time;
}
