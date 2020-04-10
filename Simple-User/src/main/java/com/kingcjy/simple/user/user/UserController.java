package com.kingcjy.simple.user.user;

import com.kingcjy.simple.user.domain.User;
import com.kingcjy.simple.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/profile")
    public User getUserProfile() {
        String uid = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUid(uid).get();
    }
}
