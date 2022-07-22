package com.spotifyplaylist.controller.impl;

import com.spotifyplaylist.controller.PlaylistController;
import com.spotifyplaylist.service.PlaylistService;
import com.spotifyplaylist.session.LoggedUser;
import org.springframework.stereotype.Controller;

@Controller
public class PlaylistControllerImpl implements PlaylistController {

    private final LoggedUser loggedUser;
    private final PlaylistService playlistService;

    public PlaylistControllerImpl(LoggedUser loggedUser, PlaylistService playlistService) {
        this.loggedUser = loggedUser;
        this.playlistService = playlistService;
    }

    @Override
    public String addSongToPlaylist(Long songId) {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        this.playlistService.addSongToUser(songId, this.loggedUser.getId());
        return "redirect:/home";
    }

    @Override
    public String removeSongFromPlaylist(Long songId) {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        this.playlistService.removeSongFromUser(songId, this.loggedUser.getId());
        return "redirect:/home";
    }

    @Override
    public String deleteAllFromPlaylist() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        this.playlistService.deleteAllSongs(this.loggedUser.getId());
        return "redirect:/home";
    }
}
