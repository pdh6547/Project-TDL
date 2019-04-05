package com.donghyun.service;

import com.donghyun.domain.ToDoList;
import com.donghyun.domain.User;
import com.donghyun.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ToDoListService {

    @Autowired
    ToDoListRepository toDoListRepository;

    public List<ToDoList> findList(User user) {
        return toDoListRepository.findAllByUserOrderByIdx(user);
    }

}
