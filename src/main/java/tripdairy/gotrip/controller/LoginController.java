package tripdairy.gotrip.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tripdairy.gotrip.session.SessionConst;
import tripdairy.gotrip.domain.Users;
import tripdairy.gotrip.service.LoginService;
import tripdairy.gotrip.service.NotCorrespondingEmailException;

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

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) throws NotCorrespondingEmailException {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Users loginUser = loginService.login(form.getEmail(), form.getPassword());
        log.info("login ? {}", loginUser);

        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

        log.info("redirect 확인용 {}", redirectURL);
        //로그인 성공
        return "redirect:" + redirectURL;
    }
}
