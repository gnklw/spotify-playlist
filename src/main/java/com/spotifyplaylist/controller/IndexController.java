package com.spotifyplaylist.controller;

import org.springframework.web.bind.annotation.GetMapping;

public interface IndexController {

    @GetMapping("/")
    String index();
}
