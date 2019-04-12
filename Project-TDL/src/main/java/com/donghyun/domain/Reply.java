package com.donghyun.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table
@Entity
@NoArgsConstructor
@Getter
@Setter
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

    public void setCreatedDateNow2() {
        this.createdDate = LocalDateTime.now();
    }

}
