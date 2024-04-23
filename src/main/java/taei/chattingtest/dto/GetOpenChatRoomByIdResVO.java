package taei.chattingtest.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class GetOpenChatRoomByIdResVO {

    private long roomId;
    private String name;
    private boolean useNotification;
    private String status;
    private List<ListChatRoomUserVO> listChatRoomUser;

    private GetOpenChatRoomByIdResVO(OpenChatRoomDTO openChatRoomDTO, List<UserDTO> chatUsers) {
        this.roomId = openChatRoomDTO.getId();
        this.name = openChatRoomDTO.getName();
        this.useNotification = openChatRoomDTO.isUseNotification();
        this.status = openChatRoomDTO.getStatus();
        this.listChatRoomUser = ListChatRoomUserVO.of(chatUsers);
    }

    public static GetOpenChatRoomByIdResVO of(OpenChatRoomDTO openChatRoomDTO, List<UserDTO> chatUsers) {
        return new GetOpenChatRoomByIdResVO(openChatRoomDTO, chatUsers);
    }
}
