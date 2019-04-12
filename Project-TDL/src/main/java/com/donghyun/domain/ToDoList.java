package com.donghyun.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "toDoList", fetch = FetchType.EAGER)
    private List<Reply> reply= new ArrayList<>();

    @Builder
    public ToDoList(String description, Boolean status, LocalDateTime createdDate, LocalDateTime completedDate, User user, List<Reply> reply) {
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.completedDate = completedDate;
        this.user = user;
        this.reply = reply;
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
