package taei.chattingtest.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ListMyExpertChatRoomVO {

    private Long roomId;
    private String expName;
    private String expPhoto;
    private String lastMsg;
    private LocalDateTime lastMsgTime;

    private ListMyExpertChatRoomVO(PrivateChatRoomDTO privateChatRoomDTO) {
        this.roomId = privateChatRoomDTO.getRoomId();
        this.expName = privateChatRoomDTO.getExpName();
        this.expPhoto = privateChatRoomDTO.getExpPhoto();
        this.lastMsg = privateChatRoomDTO.getLastMsg();
        this.lastMsgTime = privateChatRoomDTO.getLastMsgTime();
    }

    public static ListMyExpertChatRoomVO of(PrivateChatRoomDTO privateChatRoomDTO) {
        return new ListMyExpertChatRoomVO(privateChatRoomDTO);
    }

    public static List<ListMyExpertChatRoomVO> of(List<PrivateChatRoomDTO> privateChatRoomDTOS) {
        return privateChatRoomDTOS.stream().map(ListMyExpertChatRoomVO::of).collect(Collectors.toList());
    }
}
