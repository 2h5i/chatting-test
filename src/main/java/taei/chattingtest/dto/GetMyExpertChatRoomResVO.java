package taei.chattingtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetMyExpertChatRoomResVO {

    private List<ListMyExpertChatRoomVO> resultList;
}
