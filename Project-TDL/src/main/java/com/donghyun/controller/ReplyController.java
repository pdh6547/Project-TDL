package com.donghyun.controller;

import com.donghyun.domain.Reply;
import com.donghyun.domain.ReplyDto;
import com.donghyun.domain.ToDoList;
import com.donghyun.repository.ReplyRepository;
import com.donghyun.repository.ToDoListRepository;
import com.donghyun.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    ToDoListRepository toDoListRepository;

    private final ReplyRepository replyRepository;

    @Autowired
    ReplyService replyService;

    public ReplyController(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    @PostMapping("/{idx}")
    public ResponseEntity<?> postReply(Model model, @PathVariable ("idx")Integer idx, @RequestBody Reply reply) {
        ToDoList toDoList = new ToDoList();
        ToDoList tdlIdx = toDoListRepository.getOne(idx);
        reply.setCreatedDateNow();
        reply.setToDoList(tdlIdx);
        Reply reply1 = replyRepository.save(reply);
        ReplyDto replyDto = new ReplyDto(reply1);
        toDoList.add(reply);

        return new ResponseEntity<>(replyDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{idx}")
    public ResponseEntity<?> putList(@PathVariable("idx") Integer idx, @RequestBody String content) {
        Reply persistReply = replyRepository.getOne(idx);
        persistReply.update(content);
        replyRepository.save(persistReply);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idx}")
    public ResponseEntity<?> deleteReply(@PathVariable("idx") Integer idx) {
        replyRepository.deleteById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
