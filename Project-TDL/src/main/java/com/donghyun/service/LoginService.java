package com.donghyun.service;

import com.donghyun.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean duplication(String email) {
        return userRepository.findByEmail(email) == null;
    }
}
