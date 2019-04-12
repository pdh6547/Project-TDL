package com.donghyun.repository;

import com.donghyun.domain.Reply;
import com.donghyun.domain.ToDoList;
import com.donghyun.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    List<Reply> findAllByToDoListOrderByIdx(ToDoList toDoList);
}
