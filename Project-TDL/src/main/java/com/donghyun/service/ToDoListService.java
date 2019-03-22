package com.donghyun.service;

import com.donghyun.domain.ToDoList;
import com.donghyun.domain.User;
import com.donghyun.repository.ToDoListRepository;
import com.donghyun.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ToDoListService {

    @Autowired
    ToDoListRepository toDoListRepository;

    @Autowired
    UserRepository userRepository;

    public List<ToDoList> findList() {
        return toDoListRepository.findAllByOrderByIdx();
    }

    public User findUser() {return  userRepository.getOne(1);}

}
