package me.kingcjy.simple.simplepay.payment;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentViewController {

    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
    public String payment(Model model, PaymentReserveRequestDto dto) {

        SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("isLogin", !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken));
        model.addAttribute("dto", dto);
        return "payment";
    }
}
