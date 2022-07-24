package com.spotifyplaylist.role.enumeration;

public enum Roles {

    USER ("User"),
    ADMIN ("Admin");

    private final String value;

    private Roles (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
