package com.donghyun.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
//@ToString
public class ToDoList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer idx;

    @Column
    private String description;

    @Column
    private Boolean status;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime completedDate;

    @ManyToOne
    private User user;

    @Builder
    public ToDoList(String description, Boolean status, LocalDateTime createdDate, LocalDateTime completedDate, User user) {
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.completedDate = completedDate;
        this.user = user;
    }
    public void setCreatedDateNow() {
        this.createdDate = LocalDateTime.now();
    }

    public void update(String description) {
        this.description = description;
    }

    public void complete() {
        if(status==null) {
            this.status = true;
            this.completedDate = LocalDateTime.now();
        }
        else {
            this.status = null;
            this.completedDate = null;
        }
    }
}
