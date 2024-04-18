package taei.chattingtest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taei.chattingtest.dto.Chat;

@RestController
@RequestMapping("/chatting-test")
@RequiredArgsConstructor
public class ChattingTestController {

    private final MongoTemplate mongoTemplate;
    @PostMapping("/insert-msg-test")
    public void insertMsgTest(@RequestBody Chat chat) {

        mongoTemplate.insert(chat);
    }
}
