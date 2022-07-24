package com.spotifyplaylist.user.controller.impl;

import com.spotifyplaylist.user.controller.PlaylistController;
import com.spotifyplaylist.user.service.PlaylistService;
import com.spotifyplaylist.user.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

@Controller
public class PlaylistControllerImpl implements PlaylistController {

    private final UserService userService;
    private final PlaylistService playlistService;

    public PlaylistControllerImpl(UserService userService, PlaylistService playlistService) {
        this.userService = userService;
        this.playlistService = playlistService;
    }

    @Override
    public String addSongToPlaylist(Long songId, User user) {
        this.playlistService.addSongToUser(songId, getUserId(user));
        return "redirect:/home";
    }

    @Override
    public String removeSongFromPlaylist(Long songId, User user) {
        this.playlistService.removeSongFromUser(songId, getUserId(user));
        return "redirect:/home";
    }

    @Override
    public String deleteAllFromPlaylist(User user) {
        this.playlistService.deleteAllSongs(getUserId(user));
        return "redirect:/home";
    }

    private long getUserId(User user) {
        return this.userService.getUserByUsername(user.getUsername()).getId();
    }
}
