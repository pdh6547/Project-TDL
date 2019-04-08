package com.donghyun.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable {

//    @Column
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 4, max = 12)
    private String password;

//    @Column
    @NotBlank(message = "이메일 값을 입력해주세요.")
    @Email(message = "이메일 형식을 맞춰주세요.")
    private String email;

    public User toEntity() {
        User user = new User();
        user.setEmail(this.email);
        user.setPassword(this.password);

        return user;
    }
}
