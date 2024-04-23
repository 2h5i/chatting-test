package taei.chattingtest.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import taei.chattingtest.enums.MessageType;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document("chat")
public class UserChat {

    @Id
    private String id;
    private MessageType messageType;
    private Long roomId;
    private Long userId;
    private Long expertId;
    private String message;
    private String status;
    private List<String> images;
    private LocalDateTime createdAt;
}
