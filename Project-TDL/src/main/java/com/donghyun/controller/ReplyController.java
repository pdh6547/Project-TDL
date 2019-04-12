package com.donghyun.controller;

import com.donghyun.domain.Reply;
import com.donghyun.domain.ToDoList;
import com.donghyun.repository.ReplyRepository;
import com.donghyun.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reply")
public class ReplyController {

    private ToDoList all2;

    @Autowired
    ToDoListRepository toDoListRepository;

    private final ReplyRepository replyRepository;

    public ReplyController(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    @PostMapping
    public ResponseEntity<?> postToDoList(@RequestBody Reply reply) {
//        all2 = toDoListRepository.findById(reply.getIdx());
        reply.setToDoList(reply.getToDoList());
        System.out.println(reply);
        reply.setCreatedDateNow2();
        replyRepository.save(reply);
//        all.add(toDoList);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

}
