package com.spotifyplaylist.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

public interface ErrorController extends org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    String handleError(HttpServletRequest request, @AuthenticationPrincipal User user);
}
