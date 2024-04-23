package taei.chattingtest.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class ListChatVO {

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

    private ListChatVO(ListChatDTO listChatDTO) {
        this.chatId = listChatDTO.getChatId();
        this.messageType = listChatDTO.getMessageType();
        this.message = listChatDTO.getMessage();
        this.images = listChatDTO.getImages();
        this.status = listChatDTO.getStatus();
        this.createdAt = listChatDTO.getCreatedAt();
        this.userId = listChatDTO.getUserId();
        this.userProfilePhoto = Objects.isNull(listChatDTO.getUserProfilePhoto()) ? null : listChatDTO.getUserProfilePhoto().replace("/opt/temp/uploads", "");
        this.userNickName = listChatDTO.getUserNickName();
        this.expertId = listChatDTO.getExpertId();
        this.expName = listChatDTO.getExpName();
        this.expPhoto = Objects.isNull(listChatDTO.getExpPhoto()) ? null : listChatDTO.getExpPhoto().replace("/opt/temp/uploads", "");
        this.myChat = listChatDTO.isMyChat();
        this.expert = listChatDTO.isExpert();
    }

    public static ListChatVO of(ListChatDTO listChatDTO) {
        return new ListChatVO(listChatDTO);
    }

    public static List<ListChatVO> of(List<ListChatDTO> listChatDTOS) {
        return listChatDTOS.stream().map(ListChatVO::of).collect(Collectors.toList());
    }
}
