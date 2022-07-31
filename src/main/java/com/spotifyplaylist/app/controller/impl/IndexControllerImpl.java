package com.spotifyplaylist.app.controller.impl;

import com.spotifyplaylist.app.controller.IndexController;
import org.springframework.stereotype.Controller;

@Controller
public class IndexControllerImpl implements IndexController {

    public IndexControllerImpl() {
    }

    @Override
    public String index() {
        return "index";
    }
}
