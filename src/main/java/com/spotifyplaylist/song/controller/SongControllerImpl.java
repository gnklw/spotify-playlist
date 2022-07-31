package com.spotifyplaylist.song.controller;

import com.spotifyplaylist.song.controller.SongController;
import com.spotifyplaylist.song.model.dto.AddSongDTO;
import com.spotifyplaylist.song.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SongControllerImpl implements SongController {

    private final SongService songService;

    public SongControllerImpl(SongService songService) {
        this.songService = songService;
    }

    @ModelAttribute
    public AddSongDTO addSongDTO() {
        return new AddSongDTO();
    }

    @Override
    public String addSong() {
        return "song-add";
    }

    @Override
    public String addSong(AddSongDTO addSongDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addSongDTO", addSongDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addSongDTO", result);

            return "redirect:/songs/add-song";
        }

        this.songService.addSong(addSongDTO);
        return "redirect:/home";
    }
}
