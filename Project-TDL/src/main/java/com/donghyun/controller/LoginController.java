package com.donghyun.controller;

import com.donghyun.domain.UserDto;
import com.donghyun.repository.UserRepository;
import com.donghyun.service.LoginService;
import com.donghyun.service.ToDoListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class LoginController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final LoginService loginService;

    public LoginController(PasswordEncoder passwordEncoder, UserRepository userRepository, LoginService loginService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.loginService = loginService;
    }

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
    public ResponseEntity<?> postToDoList(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userDto.toEntity());
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    //회원가입시 중복 검사
    @PostMapping("/register/commit")
    public ResponseEntity<?> commitUser(@RequestBody String email) {
        boolean success = loginService.duplication(email);
        if (success == true) {
            return new ResponseEntity<>("{}", HttpStatus.CREATED);
        } else return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
    }
}