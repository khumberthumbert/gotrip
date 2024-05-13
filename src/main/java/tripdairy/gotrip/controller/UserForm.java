package tripdairy.gotrip.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import tripdairy.gotrip.domain.Users;

@Getter @Setter
public class UserForm {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String username;

    @NotEmpty(message = "비밀번호는 필수 입니다.")
    private String password;

    @NotEmpty(message = "이메일은 필수 입니다.")
    private String email;

    @NotEmpty(message = "나이는 필수 입니다.")
    private int age;

}
