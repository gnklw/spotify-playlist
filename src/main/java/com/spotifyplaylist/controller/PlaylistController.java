package com.spotifyplaylist.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface PlaylistController {

    @PostMapping("/home/add-song/{id}")
    String addSongToPlaylist(@PathVariable("id") Long id);

    @PostMapping("/home/remove-song/{id}")
    String removeSongFromPlaylist(@PathVariable("id") Long id);

    @PostMapping("/home/remove-all-song")
    String deleteAllFromPlaylist();
}
