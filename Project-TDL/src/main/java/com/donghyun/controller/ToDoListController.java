package com.donghyun.controller;

import com.donghyun.domain.ToDoList;
import com.donghyun.domain.User;
import com.donghyun.repository.ToDoListRepository;
import com.donghyun.repository.UserRepository;
import com.donghyun.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tdl")
public class ToDoListController {

    private final ToDoListService toDoListService;

    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    @Autowired
    ToDoListRepository toDoListRepository;

    @Autowired
    UserRepository userRepository;

    private User all;
    @GetMapping({"/list"})
    public String list(Model model, User user) {
        org.springframework.security.core.userdetails.User all2 = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        all = toDoListService.findUser(all2.getUsername());
        System.out.println("all.name : "+all);
        model.addAttribute("list", toDoListService.findList(all));
        return "/tdl/list";
    }

//    todoList 등록
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

    @GetMapping("/logout")
    public String logoutUser() {
        this.all = null;
        return "redirect:/login";
    }
}
