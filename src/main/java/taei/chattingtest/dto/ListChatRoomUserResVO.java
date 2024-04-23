package taei.chattingtest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ListChatRoomUserResVO {

    private List<ListChatRoomUserVO> resultList;
}
