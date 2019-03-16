package com.donghyun.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor
public class ToDoList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idx;

    @Column
    private String description;

    @Column
    private Boolean status;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime completedDate;

    @Builder
    public ToDoList(String description, Boolean status, LocalDateTime createdDate, LocalDateTime completedDate) {
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.completedDate = completedDate;
    }

    public void setCreatedDateNow() {
        this.createdDate = LocalDateTime.now();
    }

    public void update(ToDoList toDoList) {
        this.description = toDoList.getDescription();
        this.status = toDoList.getStatus();
        this.createdDate = LocalDateTime.now();
        this.completedDate = LocalDateTime.now();
    }

}
