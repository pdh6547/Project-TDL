package com.donghyun.controller;

import com.donghyun.domain.ToDoList;
import com.donghyun.domain.User;
import com.donghyun.repository.ToDoListRepository;
import com.donghyun.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tdl")
public class ToDoListController {

    private final ToDoListService toDoListService;

    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    @Autowired
    ToDoListRepository toDoListRepository;

    @GetMapping({"/list"})
    public String list(Model model) {
        model.addAttribute("list", toDoListService.findList());

        System.out.println(toDoListService.findUser());
        return "/tdl/list";
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getList() {
        List<ToDoList> toDoLists = toDoListRepository.findAll();
        return ResponseEntity.ok(toDoLists);
    }

    @PostMapping
    public ResponseEntity<?> postToDoList(@RequestBody ToDoList toDoList)    {
        toDoList.setUser(toDoListService.findUser());
        toDoList.setCreatedDateNow();
        toDoListRepository.save(toDoList);

        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    @PutMapping("/update/{idx}")
    public ResponseEntity<?> putList(@PathVariable("idx")Long idx, @RequestBody String description) {
        ToDoList persistList = toDoListRepository.getOne(idx);
        persistList.update(description);
        toDoListRepository.save(persistList);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @PutMapping("/complete/{idx}")
    public ResponseEntity<?> completeList(@PathVariable("idx")Long idx) {
        ToDoList persistList = toDoListRepository.getOne(idx);
        persistList.complete();
        toDoListRepository.save(persistList);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idx}")
    public ResponseEntity<?> deleteList(@PathVariable("idx")Long idx) {
        toDoListRepository.deleteById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
