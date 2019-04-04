package com.donghyun.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table
@Entity
@NoArgsConstructor
@ToString
public class User implements Serializable {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column
    private String password;

    @Column
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ToDoList> toDoList = new ArrayList<>();

    @Builder
    public User(String password, String email, List<ToDoList> toDoList) {
        this.password = password;
        this.email = email;
        this.toDoList = toDoList;
    }

    public void add(ToDoList toDoList){
        toDoList.setUser(this);
        this.toDoList.add(toDoList);
    }
}
