package com.spotifyplaylist.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public interface HomeController {

    @GetMapping("/")
    String index();

    @GetMapping("/home")
    String home(Model model);
}
