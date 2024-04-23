package taei.chattingtest.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ListPrivateChatRoomVO {

    private Long roomId;
    private String expName;
    private String expPhoto;
    private String lastMsg;
    private LocalDateTime lastMsgTime;

    private ListPrivateChatRoomVO(PrivateChatRoomDTO privateChatRoomDTO) {
        this.roomId = privateChatRoomDTO.getRoomId();
        this.expName = privateChatRoomDTO.getExpName();
        this.expPhoto = privateChatRoomDTO.getExpPhoto();
        this.lastMsg = privateChatRoomDTO.getLastMsg();
        this.lastMsgTime = privateChatRoomDTO.getLastMsgTime();
    }

    public static ListPrivateChatRoomVO of(PrivateChatRoomDTO privateChatRoomDTO) {
        return new ListPrivateChatRoomVO(privateChatRoomDTO);
    }

    public static List<ListPrivateChatRoomVO> of(List<PrivateChatRoomDTO> privateChatRoomDTOS) {
        return privateChatRoomDTOS.stream().map(ListPrivateChatRoomVO::of).collect(Collectors.toList());
    }
}
