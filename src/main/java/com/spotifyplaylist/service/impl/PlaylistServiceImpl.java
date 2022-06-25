package com.spotifyplaylist.service.impl;

import com.spotifyplaylist.model.entity.Song;
import com.spotifyplaylist.service.PlaylistService;
import org.springframework.stereotype.Service;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final SongServiceImpl songService;
    private final AuthServiceImpl authService;

    public PlaylistServiceImpl(SongServiceImpl songService, AuthServiceImpl authService) {
        this.songService = songService;
        this.authService = authService;
    }

    @Override
    public void addSong(Long songId, Long userId) {
        Song song = this.songService.findSongById(songId);
        this.authService.addSongToUser(userId, song);
    }

    @Override
    public void removeSong(Long songId, Long userId) {
        this.authService.removeSongFromUser(userId, songId);
    }

    @Override
    public void deleteAll(Long userId) {
        this.authService.deleteAllSongs(userId);
    }
}
