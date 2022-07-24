package com.spotifyplaylist.user.controller;

import com.spotifyplaylist.user.model.dto.RegistrationDTO;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequestMapping("/users")
public interface AuthController {

    @GetMapping("/login")
    String login();

    @GetMapping("/register")
    String register();

    @PostMapping("/register")
    String createAccount(@Valid RegistrationDTO registrationDTO/*, BindingResult result, RedirectAttributes redirectAttribute*/);

    @PostMapping("/login-error")
    String failedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes attributes
    );
}
