package com.donghyun.controller;

import com.donghyun.domain.ToDoList;
import com.donghyun.domain.User;
import com.donghyun.repository.UserRepository;
import com.donghyun.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {

    @Autowired
    ToDoListService toDoListService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "/tdl/login";
    }

    @GetMapping("/register")
    public String register() {
        return "/tdl/register";
    }
    @PostMapping("register/create")
    public ResponseEntity<?> postToDoList(@RequestBody User user)    {
        userRepository.save(user);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }
}
