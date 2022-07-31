package com.spotifyplaylist.app.controller.impl;

import com.spotifyplaylist.app.controller.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

//todo: create more error pages

@Controller
public class ErrorControllerImpl implements ErrorController {

    @Override
    public String handleError(HttpServletRequest request, User user) {
        Object status =
                request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode >= HttpStatus.INTERNAL_SERVER_ERROR.value()
                    && statusCode <= HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value()) {
                return "error-500";
            }

            if (user == null) {
                return "redirect:/";
            }

            return "redirect:/home";
        }

        return "error";
    }
}
