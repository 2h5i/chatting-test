package taei.chattingtest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taei.chattingtest.dto.*;
import taei.chattingtest.enums.MessageType;
import taei.chattingtest.repo.ChatRoomRepo;
import taei.chattingtest.utils.Pagination;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepo chatRoomRepo;
    private final MongoTemplate mongoTemplate;

    @Transactional
    public Long createRoom(String name, Long ownerId, String photo, String limitCnt, String roomType) {
        Map roomMap = new HashMap<>();
        roomMap.put("id", null);
        roomMap.put("name", name);
        roomMap.put("ownerId", ownerId);
        roomMap.put("photo", photo);
        roomMap.put("limitCnt", limitCnt);
        roomMap.put("roomType", roomType);
        roomMap.put("clientId", "test");

        chatRoomRepo.insertRoom(roomMap);
        Long roomId = Long.parseLong(String.valueOf(roomMap.get("id")));

        Map roomUserMap = new HashMap<>();
        roomUserMap.put("roomId", roomId);
        roomUserMap.put("expertId", ownerId);
        roomUserMap.put("clientId", "test");

        chatRoomRepo.insertExpertToRoom(roomUserMap);

        return roomId;
    }

    @Transactional
    public UserEnterRes enterUser(UserEnterReq userEnterDTO) {
        UserChat userChat = UserChat.builder()
                .messageType(MessageType.ENTER)
                .roomId(userEnterDTO.getRoomId())
                .userId(userEnterDTO.getUserId())
                .message(userEnterDTO.getUserNickName() + "님이 들어왔습니다.")
                .status("SHOW")
                .createdAt(LocalDateTime.now())
                .build();

        chatRoomRepo.insertUserToRoom(userEnterDTO);
        mongoTemplate.insert(userChat);

        UserEnterRes userChatRes = UserEnterRes.of(userChat, userEnterDTO);
        return userChatRes;
    }

    public UserLeaveRes leaveUser(UserLeaveReq userLeaveReq) {
        UserChat userChat = UserChat.builder()
                .messageType(MessageType.LEAVE)
                .roomId(userLeaveReq.getRoomId())
                .userId(userLeaveReq.getUserId())
                .message(userLeaveReq.getUserNickName() + "님이 나갔습니다.")
                .status("SHOW")
                .createdAt(LocalDateTime.now())
                .build();

        chatRoomRepo.deleteUserFromRoom(userLeaveReq);
        mongoTemplate.insert(userChat);

        return UserLeaveRes.of(userChat, userLeaveReq);
    }

    public ListChatRoomUserResVO listChatRoomUser(ListChatRoomUserReqVO listChatRoomUserReqVO) {
        Map reqMap = new HashMap();
        reqMap.put("roomId", listChatRoomUserReqVO.getRoomId());
        reqMap.put("userId", listChatRoomUserReqVO.getUserId());

        List<UserDTO> userDTOS = chatRoomRepo.selectListChatRoomUser(reqMap);
        List<ListChatRoomUserVO> listChatRoomUserVOS = ListChatRoomUserVO.of(userDTOS);

        return new ListChatRoomUserResVO(listChatRoomUserVOS);
    }

    public ListMyOpenChatRoomResVO listMyOpenChatRoom(ListMyOpenChatRoomReqVO listMyOpenChatRoomReqVO) {
        List<OpenChatRoomDTO> openChatRoomDTOS = chatRoomRepo.selectListMyOpenChatRoom(listMyOpenChatRoomReqVO.getUserId());

        for (int i = 0; i < openChatRoomDTOS.size(); i++) {
            OpenChatRoomDTO openChatRoomDTO = openChatRoomDTOS.get(i);
            Query query = new Query();
            query.addCriteria(Criteria.where("roomId").is(openChatRoomDTO.getId())
                    .and("messageType").is("TALK"));
            query.with(Sort.by(Sort.Direction.DESC, "createdAt"));
            query.limit(1);
            Chat chat = mongoTemplate.findOne(query, Chat.class, "chat");
            openChatRoomDTO.setLastMsgTime(Objects.isNull(chat) ? null : chat.getCreatedAt());
        }

        List<ListOpenChatRoomVO> listOpenChatRoomVOS = ListOpenChatRoomVO.of(openChatRoomDTOS);
        return new ListMyOpenChatRoomResVO(listOpenChatRoomVOS);
    }

    public ListAllOpenChatRoomResVO listAllOpenChatRoom(ListAllOpenChatRoomReqVO listAllOpenChatRoomReqVO) {
        int rowStart = (listAllOpenChatRoomReqVO.getPage() - 1) * listAllOpenChatRoomReqVO.getPageSize();

        Map reqMap = new HashMap();
        reqMap.put("pageSize", listAllOpenChatRoomReqVO.getPageSize());
        reqMap.put("rowStart", rowStart);

        long count = chatRoomRepo.countListAllOpenChatRoom(reqMap);

        Pagination pagination = new Pagination(count, listAllOpenChatRoomReqVO.getPage(), listAllOpenChatRoomReqVO.getPageSize());

        List<OpenChatRoomDTO> openChatRoomDTOS = chatRoomRepo.selectListAllOpenChatRoom(reqMap);

        for (int i = 0; i < openChatRoomDTOS.size(); i++) {
            OpenChatRoomDTO openChatRoomDTO = openChatRoomDTOS.get(i);
            Query query = new Query();
            query.addCriteria(Criteria.where("roomId").is(openChatRoomDTO.getId())
                    .and("messageType").is("TALK"));
            query.with(Sort.by(Sort.Direction.DESC, "createdAt"));
            query.limit(1);
            UserChat chat = mongoTemplate.findOne(query, UserChat.class, "chat");
            openChatRoomDTO.setLastMsgTime(Objects.isNull(chat) ? null : chat.getCreatedAt());
        }

        List<ListOpenChatRoomVO> listOpenChatRoomVOS = ListOpenChatRoomVO.of(openChatRoomDTOS);

        return new ListAllOpenChatRoomResVO(listOpenChatRoomVOS, pagination);
    }

    public Long createPrivateChatRoom(Long expertId, Long userId) {
        Map reqMap = new HashMap<>();
        reqMap.put("id", null);
        reqMap.put("expertId", expertId);
        reqMap.put("userId", userId);
        reqMap.put("clientId", "test");

        chatRoomRepo.insertPrivateRoom(reqMap);
        Long roomId = Long.parseLong(String.valueOf(reqMap.get("id")));

        return roomId;
    }

    public GetMyExpertChatRoomResVO getMyExpertChatRoom(GetMyExpertChatRoomReqVO getMyExpertChatRoomReqVO) {
        List<PrivateChatRoomDTO> privateChatRoomDTOS = chatRoomRepo.selectMyExpertChatRoom(getMyExpertChatRoomReqVO.getUserId());

        for (int i = 0; i < privateChatRoomDTOS.size(); i++) {
            PrivateChatRoomDTO privateChatRoomDTO = privateChatRoomDTOS.get(i);
            Query query = new Query();
            query.addCriteria(Criteria.where("roomId").is(privateChatRoomDTO.getRoomId())
                    .and("messageType").is("TALK"));
            query.with(Sort.by(Sort.Direction.DESC, "createdAt"));
            query.limit(1);
            PrivateUserChat privateChat = mongoTemplate.findOne(query, PrivateUserChat.class, "private_chat");
            privateChatRoomDTO.setLastMsg(Objects.isNull(privateChat) ? null : privateChat.getMessage());
            privateChatRoomDTO.setLastMsgTime(Objects.isNull(privateChat) ? null : privateChat.getCreatedAt());
        }

        return new GetMyExpertChatRoomResVO(ListMyExpertChatRoomVO.of(privateChatRoomDTOS));
    }

    public ListMyPrivateChatRoomResVO listMyPrivateChatRoom(ListMyPrivateChatRoomReqVO listMyPrivateChatRoomReqVO) {
        int rowStart = (listMyPrivateChatRoomReqVO.getPage() - 1) * listMyPrivateChatRoomReqVO.getPageSize();

        Map reqMap = new HashMap();
        reqMap.put("userId", listMyPrivateChatRoomReqVO.getUserId());
        reqMap.put("pageSize", listMyPrivateChatRoomReqVO.getPageSize());
        reqMap.put("rowStart", rowStart);

        long count = chatRoomRepo.countListMyPrivateChatRoom(reqMap);

        Pagination pagination = new Pagination(count, listMyPrivateChatRoomReqVO.getPage(), listMyPrivateChatRoomReqVO.getPageSize());

        List<PrivateChatRoomDTO> privateChatRoomDTOS = chatRoomRepo.selectListMyPrivateChatRoom(reqMap);

        for (int i = 0; i < privateChatRoomDTOS.size(); i++) {
            PrivateChatRoomDTO privateChatRoomDTO = privateChatRoomDTOS.get(i);
            Query query = new Query();
            query.addCriteria(Criteria.where("roomId").is(privateChatRoomDTO.getRoomId())
                    .and("messageType").is("TALK"));
            query.with(Sort.by(Sort.Direction.DESC, "createdAt"));
            query.limit(1);
            PrivateUserChat chat = mongoTemplate.findOne(query, PrivateUserChat.class, "private_chat");
            privateChatRoomDTO.setLastMsgTime(Objects.isNull(chat) ? null : chat.getCreatedAt());
            privateChatRoomDTO.setLastMsg(Objects.isNull(chat) ? null : chat.getMessage());
        }

        List<ListPrivateChatRoomVO> listPrivateChatRoomVOS = ListPrivateChatRoomVO.of(privateChatRoomDTOS);

        return new ListMyPrivateChatRoomResVO(listPrivateChatRoomVOS, pagination);
    }

    public GetOpenChatRoomByIdResVO getOpenChatRoomById(GetOpenChatRoomByIdReqVO getOpenChatRoomByIdReqVO) {
        OpenChatRoomDTO openChatRoomDTO = chatRoomRepo.selectOpenChatRoomById(getOpenChatRoomByIdReqVO);

        Map reqMap = new HashMap<>();
        reqMap.put("roomId", getOpenChatRoomByIdReqVO.getRoomId());
        reqMap.put("userId", getOpenChatRoomByIdReqVO.getUserId());
        List<UserDTO> userDTOS = chatRoomRepo.selectListChatRoomUser(reqMap);

        if (Objects.isNull(openChatRoomDTO)) {
            // 에러처리
        }

        return GetOpenChatRoomByIdResVO.of(openChatRoomDTO, userDTOS);
    }

    public SetChatRoomNotificationResVO setChatRoomNotification(SetChatRoomNotificationReqVO setChatRoomNotificationReqVO) {
        chatRoomRepo.updateRoomNotification(setChatRoomNotificationReqVO);

        return new SetChatRoomNotificationResVO(setChatRoomNotificationReqVO.getRoomId());
    }
}
