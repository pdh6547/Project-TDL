package com.donghyun.service;

import com.donghyun.domain.Reply;
import com.donghyun.domain.ToDoList;
import com.donghyun.domain.User;
import com.donghyun.repository.ReplyRepository;
import com.donghyun.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    ToDoListRepository toDoListRepository;

    public List<Reply> findReply(ToDoList toDoList) {
        return replyRepository.findAllByToDoListOrderByIdx(toDoList);
    }

    public ToDoList findToDoList(Integer idx) {
        return toDoListRepository.findByIdx(idx);
    }

}
