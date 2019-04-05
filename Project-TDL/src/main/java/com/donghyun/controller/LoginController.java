package com.donghyun.controller;

import com.donghyun.domain.User;
import com.donghyun.repository.UserRepository;
import com.donghyun.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ToDoListService toDoListService;

    //로그인 페이지로 이동
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    //로그인 성공시 list 페이지로 이동
    @GetMapping("/loginSuccess")
    public String loginComplete() {
        System.out.println("로그인 성공");
        return "redirect:/tdl/list";
    }

    //회원가입 페이지로 이동
    @GetMapping("/register")
    public String register() {
        return "/register";
    }

    //회원가입 성공시 User에 저장 (Password를 암호화)
    @PostMapping("register/create")
    public ResponseEntity<?> postToDoList(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    //회원가입시 중복 검사
    @PostMapping("/register/commit")
    public ResponseEntity<?> commitUser(@RequestBody User user) {
        System.out.println(user.getEmail());
        String email = user.getEmail();
        User createUser = userRepository.findByEmail(email);

        if (createUser == null) {
            return new ResponseEntity<>("{}", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
    }
}