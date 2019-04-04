package com.donghyun.controller;

import com.donghyun.domain.User;
import com.donghyun.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/loginSuccess")
    public String loginComplete() {
        System.out.println("로그인 성공");

        return "redirect:/tdl/list";
    }

    @GetMapping("/register")
    public String register() {
        return "/register";
    }

    @PostMapping("register/create")
    public ResponseEntity<?> postToDoList(@RequestBody User user)    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }
}
