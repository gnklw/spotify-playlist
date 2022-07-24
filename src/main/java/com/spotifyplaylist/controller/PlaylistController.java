package com.spotifyplaylist.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/home")
public interface PlaylistController {

    @PostMapping("/add-song/{id}")
    String addSongToPlaylist(@PathVariable("id") Long songId, @AuthenticationPrincipal User user);

    @PostMapping("/remove-song/{id}")
    String removeSongFromPlaylist(@PathVariable("id") Long songId, @AuthenticationPrincipal User user);

    @PostMapping("/remove-all-song")
    String deleteAllFromPlaylist(@AuthenticationPrincipal User user);
}
