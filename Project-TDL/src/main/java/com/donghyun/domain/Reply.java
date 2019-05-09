package com.donghyun.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Reply implements Serializable {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column
    private String content;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @ManyToOne
    private ToDoList toDoList;

    @Builder
    public Reply(Integer idx, String content, LocalDateTime createdDate, LocalDateTime updatedDate, ToDoList toDoList) {
        this.idx = idx;
        this.content = content;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.toDoList = toDoList;
    }

    public void update(String content) {
        this.content = content;
    }

    public void setCreatedDateNow() {
        this.createdDate = LocalDateTime.now();
    }
}
