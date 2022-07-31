package com.spotifyplaylist.app.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/error")
public interface ErrorController extends org.springframework.boot.web.servlet.error.ErrorController {

    @GetMapping
    String handleError(HttpServletRequest request, @AuthenticationPrincipal User user);
}
