package com.spotifyplaylist.style.service;

import com.spotifyplaylist.style.model.entity.Style;
import com.spotifyplaylist.style.enumeration.Styles;

public interface StyleService {

    void initStyles();

    Style getStyleByName(Styles styleName);
}
