package com.spotifyplaylist.style.service.impl;

import com.spotifyplaylist.style.model.entity.Style;
import com.spotifyplaylist.style.enumeration.Styles;
import com.spotifyplaylist.style.repo.StyleRepo;
import com.spotifyplaylist.style.service.StyleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StyleServiceImpl implements StyleService {

    private final StyleRepo repo;

    public StyleServiceImpl(StyleRepo repo) {
        this.repo = repo;
    }

    @Override
    public void initStyles() {
        if (this.repo.count() != 0) {
            return;
        }

        Arrays.stream(Styles.values())
                .forEach(style -> this.repo.save(new Style().setStyleName(style).setDescription("...")));
    }

    @Override
    public Style getStyleByName(Styles styleName) {
        return this.repo.findByStyleName(styleName)
                .orElseThrow(() -> new NullPointerException("The style {" + styleName + "} was not found."));
    }
}
