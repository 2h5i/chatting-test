package taei.chattingtest.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long userId;
    private String userNickName;
    private String userProfilePhoto;
    private Long expertId;
    private String expName;
    private String expPhoto;
    private boolean expert;
    private boolean myAccount;
}
