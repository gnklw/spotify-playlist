package com.spotifyplaylist.controller.impl;

import com.spotifyplaylist.controller.HomeController;
import com.spotifyplaylist.model.dto.SongDTO;
import com.spotifyplaylist.model.dto.SongsByGenreDTO;
import com.spotifyplaylist.service.impl.HomeServiceImpl;
import com.spotifyplaylist.service.impl.PlaylistServiceImpl;
import com.spotifyplaylist.service.impl.SongServiceImpl;
import com.spotifyplaylist.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Set;

@Controller
public class HomeControllerImpl implements HomeController {

    private final LoggedUser loggedUser;
    private final HomeServiceImpl homeService;
    private final SongServiceImpl songService;
    private final PlaylistServiceImpl playlistService;

    public HomeControllerImpl(LoggedUser loggedUser, HomeServiceImpl homeService, SongServiceImpl songService, PlaylistServiceImpl playlistService) {
        this.loggedUser = loggedUser;
        this.homeService = homeService;
        this.songService = songService;
        this.playlistService = playlistService;
    }

    @Override
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @Override
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        Set<SongDTO> playlist = this.songService.getPlaylist(loggedUser.getId());

        int sumSeconds = playlist.stream().mapToInt(SongDTO::getDuration).sum();
        double minutes = Math.floor(sumSeconds / 60.0);
        double seconds = sumSeconds % 60;
        String totalDurationOfPlaylist = String.format("%.0f:%.0f", minutes, seconds);

        model.addAttribute("songs", this.homeService.getSongs());
        model.addAttribute("playlist", playlist);
        model.addAttribute("totalDurationOfPlaylist", totalDurationOfPlaylist);

        return "home";
    }

    @Override
    public String addSongToPlaylist(Long id) {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        this.playlistService.addSong(id, this.loggedUser.getId());
        return "redirect:/home";
    }

    @Override
    public String removeSongFromPlaylist(Long id) {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        this.playlistService.removeSong(id, this.loggedUser.getId());
        return "redirect:/home";
    }

    @Override
    public String deleteAllFromPlaylist() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        this.playlistService.deleteAll(this.loggedUser.getId());
        return "redirect:/home";
    }

    @ModelAttribute
    public SongsByGenreDTO songs() {
        return new SongsByGenreDTO();
    }
}
