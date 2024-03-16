package tripdairy.gotrip.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {

    @NotEmpty(message = "이메일을 입력하세요.")
    private String email;

    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password;
}
