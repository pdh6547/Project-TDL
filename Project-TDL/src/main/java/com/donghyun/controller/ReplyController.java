package com.donghyun.controller;

import com.donghyun.domain.Reply;
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
        replyRepository.save(reply);
        toDoList.add(reply);
        System.out.println("reply : "+reply);
        System.out.println("getReplies : " + toDoList.getReplies());

        System.out.println("findReply : "+replyService.findReply(tdlIdx));
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

//    @GetMapping({"/list"})
//    public String reply(Model model) {
//        ToDoList toDoList = new ToDoList();
//        ToDoList tdlIdx = toDoListRepository.getOne(1);
//        model.addAttribute("reply", replyService.findReply(tdlIdx));
//        model.addAttribute("reply", replyService.findReply(tdlIdx));
//
//        return "redirect:/tdl/list";
//    }
}
