package taei.chattingtest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import taei.chattingtest.utils.Pagination;

import java.util.List;

@Getter
@AllArgsConstructor
public class ListMyPrivateChatRoomResVO {

    private List<ListPrivateChatRoomVO> resultList;
    private Pagination pagination;
}
