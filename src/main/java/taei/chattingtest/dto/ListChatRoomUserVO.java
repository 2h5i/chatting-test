package taei.chattingtest.dto;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ListChatRoomUserVO {

    private boolean expert;
    private Long userId;
    private String userNickName;
    private String userProfilePhoto;
    private Long expertId;
    private String expName;
    private String expPhoto;
    private boolean myAccount;

    private ListChatRoomUserVO(UserDTO userDTO) {
        this.expert = userDTO.isExpert();
        this.userId = userDTO.getUserId();
        this.userNickName = userDTO.getUserNickName();
        this.userProfilePhoto = userDTO.getUserProfilePhoto();
        this.expertId = userDTO.getExpertId();
        this.expName = userDTO.getExpName();
        this.expPhoto = userDTO.getExpPhoto();
        this.myAccount = userDTO.isMyAccount();
    }

    public static ListChatRoomUserVO of(UserDTO userDTO) {
        return new ListChatRoomUserVO(userDTO);
    }

    public static List<ListChatRoomUserVO> of(List<UserDTO> userDTOS) {
        return userDTOS.stream().map(ListChatRoomUserVO::of).collect(Collectors.toList());
    }
}
