package com.spotifyplaylist.controller.impl;

import com.spotifyplaylist.controller.HomeController;
import com.spotifyplaylist.model.dto.SongDTO;
import com.spotifyplaylist.model.entity.Styles;
import com.spotifyplaylist.service.SongService;
import com.spotifyplaylist.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Set;

@Controller
public class HomeControllerImpl implements HomeController {

    private final LoggedUser loggedUser;
    private final SongService songService;

    public HomeControllerImpl(LoggedUser loggedUser, SongService songService) {
        this.loggedUser = loggedUser;
        this.songService = songService;
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
        String totalDurationOfPlaylist = this.calcTotalDuration(playlist);

        model
                .addAttribute("popSongs", this.songService.getSongsByStyle(Styles.POP))
                .addAttribute("rockSongs", this.songService.getSongsByStyle(Styles.ROCK))
                .addAttribute("jazzSongs", this.songService.getSongsByStyle(Styles.JAZZ))
                .addAttribute("playlist", playlist)
                .addAttribute("totalDurationOfPlaylist", totalDurationOfPlaylist);

        return "home";
    }

    private String calcTotalDuration(Set<SongDTO> playlist) {
        int sumSeconds = playlist.stream().mapToInt(SongDTO::getDuration).sum();
        int minutes = (int) Math.floor(sumSeconds / 60.0);
        int seconds = sumSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
