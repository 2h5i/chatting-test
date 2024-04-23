package taei.chattingtest.repo;

import org.apache.ibatis.annotations.Mapper;
import taei.chattingtest.dto.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChatRoomRepo {
    void insertRoom(Map roomMap);

    void insertExpertToRoom(Map roomUserMap);

    void insertUserToRoom(UserEnterReq userChatDTO);

    void deleteUserFromRoom(UserLeaveReq userLeaveReq);

    List<UserDTO> selectListChatRoomUser(Map reqMap);

    List<OpenChatRoomDTO> selectListMyOpenChatRoom(Long userId);

    long countListAllOpenChatRoom(Map reqMap);

    List<OpenChatRoomDTO> selectListAllOpenChatRoom(Map reqMap);

    void insertPrivateRoom(Map reqMap);

    List<PrivateChatRoomDTO> selectMyExpertChatRoom(Long userId);

    long countListMyPrivateChatRoom(Map reqMap);

    List<PrivateChatRoomDTO> selectListMyPrivateChatRoom(Map reqMap);

    OpenChatRoomDTO selectOpenChatRoomById(GetOpenChatRoomByIdReqVO getOpenChatRoomByIdReqVO);

    void updateRoomNotification(SetChatRoomNotificationReqVO setChatRoomNotificationReqVO);

    UserDTO selectUserInfo(Long userId);

    UserDTO selectExpertInfo(Long expertId);
}
