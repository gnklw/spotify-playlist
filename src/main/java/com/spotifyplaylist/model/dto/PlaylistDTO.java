package com.spotifyplaylist.model.dto;

import java.util.Set;

public class PlaylistDTO {

    private Set<SongDTO> playlist;

    public PlaylistDTO() {
    }

    public Set<SongDTO> getPlaylist() {
        return playlist;
    }

    public PlaylistDTO setPlaylist(Set<SongDTO> playlist) {
        this.playlist = playlist;
        return this;
    }
}
