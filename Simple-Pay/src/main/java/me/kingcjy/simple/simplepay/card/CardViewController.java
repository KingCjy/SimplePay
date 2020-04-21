package me.kingcjy.simple.simplepay.card;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/card")
public class CardViewController {
    
    @GetMapping("/registration")
    public String registration(Model model) {
        return "card/registration";
    }
}
