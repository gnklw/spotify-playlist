package com.spotifyplaylist.controller.impl;

import com.spotifyplaylist.controller.HomeController;
import com.spotifyplaylist.song.model.dto.SongDTO;
import com.spotifyplaylist.style.enumeration.Styles;
import com.spotifyplaylist.song.service.SongService;
import com.spotifyplaylist.user.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Set;

@Controller
public class HomeControllerImpl implements HomeController {

    private final UserService userService;
    private final SongService songService;

    public HomeControllerImpl(UserService userService, SongService songService) {
        this.userService = userService;
        this.songService = songService;
    }

    @Override
    public String home(Model model, User user) {
        Set<SongDTO> playlist =
                this.songService.getPlaylist(getUserId(user));
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

    private long getUserId(User user) {
        return this.userService.getUserByUsername(user.getUsername()).getId();
    }
}
