package tripdairy.gotrip.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import tripdairy.gotrip.service.LoginService;

@Slf4j
@Controller
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    //loginForm에서 객체를 사용하기 위해 @ModelAttribute에 LoginForm 객체를 넣었음.
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @GetMapping("/signup")
    public String signup(@ModelAttribute("userForm") UserForm userForm) {
        return "login/signup";
    }


}
