package taei.chattingtest.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ListOpenChatRoomVO {

    private Long chatRoomId;
    private String name;
    private String roomType;
    private String photo;
    private int currentUserCnt;
    private int limitUserCnt;
    private LocalDateTime lastMsgTime;

    private ListOpenChatRoomVO(OpenChatRoomDTO openChatRoomDTO) {
        this.chatRoomId = openChatRoomDTO.getId();
        this.name = openChatRoomDTO.getName();
        this.roomType = openChatRoomDTO.getRoomType();
        this.photo = openChatRoomDTO.getPhoto();
        this.currentUserCnt = openChatRoomDTO.getCurrentUserCnt();
        this.limitUserCnt = openChatRoomDTO.getLimitUserCnt();
        this.lastMsgTime = openChatRoomDTO.getLastMsgTime();
    }

    public static ListOpenChatRoomVO of(OpenChatRoomDTO openChatRoomDTO) {
        return new ListOpenChatRoomVO(openChatRoomDTO);
    }

    public static List<ListOpenChatRoomVO> of(List<OpenChatRoomDTO> openChatRoomDTOS) {
        return openChatRoomDTOS.stream().map(ListOpenChatRoomVO::of).collect(Collectors.toList());
    }
}
