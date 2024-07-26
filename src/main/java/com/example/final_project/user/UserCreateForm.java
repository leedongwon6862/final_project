package com.example.final_project.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

@Getter
@Setter
public class UserCreateForm {



    @Size(min=3,max=12)
    @NotEmpty(message = "ID는 필수 항목 입니다.")
    private String username;
    @NotEmpty(message = "비번은 필수 항목 입니다.")
    private String password1;
    @NotEmpty(message = "비번 확인 부탁 합니다.")
    private String password2;
    @Email
    @NotEmpty(message = "이메일 은 필수 항목 입니다.")
    private String email;
}
