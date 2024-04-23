package taei.chattingtest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import taei.chattingtest.dto.*;
import taei.chattingtest.enums.MessageType;
import taei.chattingtest.repo.ChatRoomRepo;
import taei.chattingtest.utils.Pagination;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepo chatRoomRepo;
    private final MongoTemplate mongoTemplate;
    public UserChatRes sendUserMessage(UserChatReq userChatReq) {
        UserChat userChat = UserChat.builder()
                .messageType(MessageType.TALK)
                .roomId(userChatReq.getRoomId())
                .userId(userChatReq.getUserId())
                .message(userChatReq.getMessage())
                .images(userChatReq.getImages())
                .status("SHOW")
                .createdAt(LocalDateTime.now())
                .build();

        mongoTemplate.insert(userChat);

        return UserChatRes.of(userChat, userChatReq);
    }

    public UserPrivateChatResVO sendUserPrivateMessage(UserPrivateChatReqVO userPrivateChatReqVO) {
        PrivateUserChat privateUserChat = PrivateUserChat.builder()
                .messageType(MessageType.TALK)
                .roomId(userPrivateChatReqVO.getRoomId())
                .userId(userPrivateChatReqVO.getUserId())
                .message(userPrivateChatReqVO.getMessage())
                .images(userPrivateChatReqVO.getImages())
                .status("SHOW")
                .createdAt(LocalDateTime.now())
                .build();

        mongoTemplate.insert(privateUserChat);

        return UserPrivateChatResVO.of(privateUserChat, userPrivateChatReqVO);
    }

    public ListOpenChatResVO listOpenChat(ListOpenChatReqVO listChatReqVO) {
        int rowStart = (listChatReqVO.getPage() - 1) * listChatReqVO.getPageSize();

        Query countQuery = new Query();
        countQuery.addCriteria(Criteria.where("roomId").is(listChatReqVO.getRoomId()));

        long count = mongoTemplate.count(countQuery, Long.class, "chat");

        Pagination pagination = new Pagination(count, listChatReqVO.getPage(), listChatReqVO.getPageSize());

        Query listQuery = new Query();
        listQuery.addCriteria(Criteria.where("roomId").is(listChatReqVO.getRoomId()))
                .with(Sort.by(Sort.Direction.DESC, "createdAt"))
                .skip(rowStart).limit(listChatReqVO.getPageSize());
        List<UserChat> userChats = mongoTemplate.find(listQuery, UserChat.class, "chat");

        List<ListChatDTO> listChatDTOS = new ArrayList<>();
        for (int i = 0; i < userChats.size(); i++) {
            UserChat userChat = userChats.get(i);

            UserDTO userDTO = new UserDTO();
            if (Objects.nonNull(userChat.getUserId())) {
                userDTO = chatRoomRepo.selectUserInfo(userChat.getUserId());
                userDTO.setExpert(false);
            } else {
                userDTO = chatRoomRepo.selectExpertInfo(userChat.getExpertId());
                userDTO.setExpert(true);
            }

            ListChatDTO listChatDTO = ListChatDTO.builder()
                    .chatId(userChat.getId())
                    .messageType(userChat.getMessageType().toString())
                    .message(userChat.getMessage())
                    .images(userChat.getImages())
                    .status(userChat.getStatus())
                    .createdAt(userChat.getCreatedAt())
                    .userId(userChat.getUserId())
                    .userNickName(userDTO.getUserNickName())
                    .userProfilePhoto(userDTO.getUserProfilePhoto())
                    .expertId(userDTO.getExpertId())
                    .expName(userDTO.getExpName())
                    .expPhoto(userDTO.getExpPhoto())
                    .myChat(listChatReqVO.getUserId() == userChat.getUserId())
                    .expert(Objects.nonNull(userDTO.getExpertId()))
                    .build();
            listChatDTOS.add(listChatDTO);
        }

        List<ListChatVO> listChatVOS = ListChatVO.of(listChatDTOS);

        return new ListOpenChatResVO(listChatVOS, pagination);
    }

    public ListPrivateChatResVO listPrivateChat(ListPrivateChatReqVO listPrivateChatReqVO) {
        int rowStart = (listPrivateChatReqVO.getPage() - 1) * listPrivateChatReqVO.getPageSize();

        Query countQuery = new Query();
        countQuery.addCriteria(Criteria.where("roomId").is(listPrivateChatReqVO.getRoomId()));

        long count = mongoTemplate.count(countQuery, Long.class, "private_chat");

        Pagination pagination = new Pagination(count, listPrivateChatReqVO.getPage(), listPrivateChatReqVO.getPageSize());

        Query listQuery = new Query();
        listQuery.addCriteria(Criteria.where("roomId").is(listPrivateChatReqVO.getRoomId()))
                .with(Sort.by(Sort.Direction.DESC, "createdAt"))
                .skip(rowStart).limit(listPrivateChatReqVO.getPageSize());
        List<PrivateUserChat> userChats = mongoTemplate.find(listQuery, PrivateUserChat.class, "private_chat");

        List<ListChatDTO> listChatDTOS = new ArrayList<>();
        for (int i = 0; i < userChats.size(); i++) {
            PrivateUserChat userChat = userChats.get(i);

            UserDTO userDTO = new UserDTO();
            if (Objects.nonNull(userChat.getUserId())) {
                userDTO = chatRoomRepo.selectUserInfo(userChat.getUserId());
                userDTO.setExpert(false);
            } else {
                userDTO = chatRoomRepo.selectExpertInfo(userChat.getExpertId());
                userDTO.setExpert(true);
            }

            ListChatDTO listChatDTO = ListChatDTO.builder()
                    .chatId(userChat.getId())
                    .messageType(userChat.getMessageType().toString())
                    .message(userChat.getMessage())
                    .images(userChat.getImages())
                    .status(userChat.getStatus())
                    .createdAt(userChat.getCreatedAt())
                    .userId(userChat.getUserId())
                    .userNickName(userDTO.getUserNickName())
                    .userProfilePhoto(userDTO.getUserProfilePhoto())
                    .expertId(userDTO.getExpertId())
                    .expName(userDTO.getExpName())
                    .expPhoto(userDTO.getExpPhoto())
                    .myChat(listPrivateChatReqVO.getUserId() == userChat.getUserId())
                    .expert(Objects.nonNull(userDTO.getExpertId()))
                    .build();
            listChatDTOS.add(listChatDTO);
        }

        List<ListChatVO> listChatVOS = ListChatVO.of(listChatDTOS);

        return new ListPrivateChatResVO(listChatVOS, pagination);
    }
}
