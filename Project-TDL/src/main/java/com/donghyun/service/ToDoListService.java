package com.donghyun.service;

import com.donghyun.domain.ToDoList;
import com.donghyun.repository.ToDoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListService {

    private final ToDoListRepository toDoListRepository;

    public ToDoListService(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }

    public List<ToDoList> findList() {
        return toDoListRepository.findAllByOrderByIdx();
    }

//    public ToDoList findByIdx(Long idx) {
//        return toDoListRepository.findById(idx).orElse(new ToDoList());
//    }
}
