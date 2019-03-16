package com.donghyun.repository;

import com.donghyun.domain.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
    public List<ToDoList> findAllByOrderByIdx();
}
