package com.donghyun.controller;

import com.donghyun.domain.ToDoList;
import com.donghyun.domain.User;
import com.donghyun.repository.ToDoListRepository;
import com.donghyun.service.ReplyService;
import com.donghyun.service.ToDoListService;
import com.donghyun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tdl")
public class ToDoListController {

    @Autowired
    ToDoListService toDoListService;

    @Autowired
    UserService userService;

    @Autowired
    ToDoListRepository toDoListRepository;


    @Autowired
    ReplyService replyService;

    private User all;

    //list 페이지 이동
    @GetMapping({"/list"})
    public String list(Model model, User user) {
        org.springframework.security.core.userdetails.User all2 = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        all = userService.findUser(all2.getUsername());
        model.addAttribute("list", toDoListService.findList(all));
        return "/tdl/list";
    }

    //todoList 등록
    @PostMapping
    public ResponseEntity<?> postToDoList(@RequestBody ToDoList toDoList) {
        toDoList.setUser(all);
        toDoList.setCreatedDateNow();
        toDoListRepository.save(toDoList);
        all.add(toDoList);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    //todoList 수정
    @PutMapping("/update/{idx}")
    public ResponseEntity<?> putList(@PathVariable("idx") Integer idx, @RequestBody String description) {
        ToDoList persistList = toDoListRepository.getOne(idx);
        persistList.update(description);
        toDoListRepository.save(persistList);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    //todoList 완료
    @PutMapping("/complete/{idx}")
    public ResponseEntity<?> completeList(@PathVariable("idx") Integer idx) {
        ToDoList persistList = toDoListRepository.getOne(idx);
        persistList.complete();
        toDoListRepository.save(persistList);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    //todoList 삭제
    @DeleteMapping("/delete/{idx}")
    public ResponseEntity<?> deleteList(@PathVariable("idx") Integer idx) {
        toDoListRepository.deleteById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
