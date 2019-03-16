package com.donghyun.controller;

import com.donghyun.domain.ToDoList;
import com.donghyun.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ToDoListRestController {
    @Autowired
    ToDoListRepository toDoListRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getList() {
        List<ToDoList> toDoLists = toDoListRepository.findAll();
        return ResponseEntity.ok(toDoLists);
    }

    @PostMapping
    public ResponseEntity<?> postToDoList(@RequestBody ToDoList toDoList)    {

        toDoList.setCreatedDateNow();
        toDoListRepository.save(toDoList);

        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    @PutMapping("/{idx}")
    public ResponseEntity<?> putList(@PathVariable("idx")Long idx, @RequestBody ToDoList toDoList) {
        ToDoList persistList = toDoListRepository.getOne(idx);
        persistList.update(toDoList);
        toDoListRepository.save(persistList);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deleteList(@PathVariable("idx")Long idx) {
        toDoListRepository.deleteById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
