package tripdairy.gotrip.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tripdairy.gotrip.domain.Users;
import tripdairy.gotrip.service.UsersService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UsersService usersService;

    @GetMapping(value = "/users/new")
    public String createForm(Model model) {
        // 화면에서 UserForm 객체에 접근할 수 있게 됨.
        model.addAttribute("userForm", new UserForm());
        return "users/createUserForm";
    }

    @PostMapping(value = "/users/new")
    public String create(@Valid UserForm form, BindingResult result) {

        //그냥 Users에다가 다 넣어서 꺼내고 서비스로 저장하면 되지 왜 form을 하는가?
        //form을 사용함으로써, 필요한 것만 넘겨주기 위함임. 안그러면 Users 도메인이 너무 지저분해지니까.
        if(result.hasErrors()) {
            return "users/createUserForm";
        }
        Users users = new Users();
        users.setName(form.getName());
        users.setPassword(form.getPassword());
        users.setEmail(form.getEmail());

        usersService.join(users);
        return "redirect:/";
    }
}
