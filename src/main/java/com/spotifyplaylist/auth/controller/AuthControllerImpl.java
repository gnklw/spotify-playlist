package com.spotifyplaylist.auth.controller;

import com.spotifyplaylist.auth.model.dto.RegistrationDTO;
import com.spotifyplaylist.auth.service.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//todo: registration and login - implement validation

@Controller
public class AuthControllerImpl implements AuthController {

    private final RegistrationService registrationService;

    public AuthControllerImpl(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @ModelAttribute
    public RegistrationDTO registrationDTO() {
        return new RegistrationDTO();
    }

    @ModelAttribute
    public void addAttribute(Model model) {
        model
                .addAttribute("badCredentials")
                .addAttribute("username");
    }

    @Override
    public String login() {
        return "login";
    }

    @Override
    public String register() {
        return "register";
    }

    @Override
    public String createAccount(RegistrationDTO registrationDTO/*, BindingResult result, RedirectAttributes redirectAttributes*/) {
//
//        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
//            result.addError(
//                    new FieldError(
//                            "differentConfirmPassword",
//                            "confirmPassword",
//                            "Passwords must be the same."));
//        }
//
//        if (result.hasErrors()) {
//            redirectAttributes
//                    .addFlashAttribute("registrationDTO", registrationDTO)
//                    .addFlashAttribute("org.springframework.validation.BindingResult.registrationDTO", result);
//
//            return "redirect:/users/register";
//        }

        this.registrationService.createAccount(registrationDTO);
        return "redirect:/home";
    }

    @Override
    public String failedLogin(String username, RedirectAttributes attributes) {
        attributes.addFlashAttribute("badCredentials", true);
        attributes.addFlashAttribute("username", username);
        return "redirect:/users/login";
    }
}
