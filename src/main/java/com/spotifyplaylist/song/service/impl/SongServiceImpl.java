package com.spotifyplaylist.song.service.impl;

import com.spotifyplaylist.song.model.dto.AddSongDTO;
import com.spotifyplaylist.song.model.dto.SongDTO;
import com.spotifyplaylist.song.model.entity.Song;
import com.spotifyplaylist.style.model.entity.Style;
import com.spotifyplaylist.style.enumeration.Styles;
import com.spotifyplaylist.song.model.mapper.SongMapper;
import com.spotifyplaylist.song.repo.SongRepo;
import com.spotifyplaylist.song.service.SongService;
import com.spotifyplaylist.style.service.StyleService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepo repo;
    private final StyleService styleService;
    private final SongMapper songMapper;

    public SongServiceImpl(SongRepo repo, StyleService styleService, SongMapper songMapper) {
        this.repo = repo;
        this.styleService = styleService;
        this.songMapper = songMapper;
    }

    @Override
    public void addSong(AddSongDTO addSongDTO) {
        Song song = this.songMapper.toSong(addSongDTO);
        song.setStyle(this.getStyleByName(addSongDTO.getStyle()));
        this.repo.save(song);
    }

    @Override
    public Song getSongById(Long id) {
        return this.repo.findById(id)
                .orElseThrow(() -> new NullPointerException("The song with id {" + id + "} was not found."));
    }

    @Override
    public Set<SongDTO> getSongsByStyle(Styles styleName) {
        Style style = this.getStyleByName(styleName);
        return this.repo.findByStyle(style)
                .stream()
                .map(this.songMapper::toSongDTO)
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Set<SongDTO> getPlaylist(Long userId) {
        return this.repo.findAllByUserId(userId)
                .stream()
                .map(this.songMapper::toSongDTO)
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private Style getStyleByName(Styles styleName) {
        return this.styleService.getStyleByName(styleName);
    }
}
