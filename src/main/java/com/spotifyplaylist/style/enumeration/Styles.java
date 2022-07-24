package com.spotifyplaylist.style.enumeration;

public enum Styles {

    POP ("Pop"),
    ROCK ("Rock"),
    JAZZ ("Jazz");

    private final String value;

    private Styles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
