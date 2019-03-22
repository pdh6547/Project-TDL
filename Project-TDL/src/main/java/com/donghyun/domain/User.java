package com.donghyun.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
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

    @Builder
    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
