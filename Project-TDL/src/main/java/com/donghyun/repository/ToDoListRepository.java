package com.donghyun.repository;

import com.donghyun.domain.ToDoList;
import com.donghyun.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {
    List<ToDoList> findAllByUserOrderByIdx(User user);
}
