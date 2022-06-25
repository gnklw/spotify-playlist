package com.spotifyplaylist.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Style extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private Styles styleName;

    @Column
    private String description;

    @OneToMany(mappedBy = "style", fetch = FetchType.LAZY)
    private Set<Song> songs;

    public Style() {
    }

    public Styles getStyleName() {
        return styleName;
    }

    public Style setStyleName(Styles styleName) {
        this.styleName = styleName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Style setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public Style setSongs(Set<Song> songs) {
        this.songs = songs;
        return this;
    }
}
