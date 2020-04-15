package me.kingcjy.simple.simplepay.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginViewController {

    @GetMapping("/login/finish")
    public String loginFinish(Model model) {
        return "login/finish";
    }
}
