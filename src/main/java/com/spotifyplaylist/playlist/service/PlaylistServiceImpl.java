package com.spotifyplaylist.playlist.service;

import com.spotifyplaylist.song.model.entity.Song;
import com.spotifyplaylist.user.model.entity.User;
import com.spotifyplaylist.playlist.service.PlaylistService;
import com.spotifyplaylist.song.service.SongService;
import com.spotifyplaylist.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final SongService songService;
    private final UserService userService;

    public PlaylistServiceImpl(SongService songService, UserService userService) {
        this.songService = songService;
        this.userService = userService;
    }

    @Override
    public void addSongToUser(Long songId, Long userId) {
        Song song = this.getSongById(songId);
        User user = this.getUserById(userId);
        if (user.addSongToPlaylist(song)) {
            this.userService.save(user);
        }
    }

    @Override
    public void removeSongFromUser(Long songId, Long userId) {
        Song song = this.getSongById(songId);
        User user = this.getUserById(userId);
        if (user.removeSongFromPlaylist(song)) {
            this.userService.save(user);
        }
    }

    @Override
    public void deleteAllSongs(Long userId) {
        User user = this.getUserById(userId);
        if (user.deleteAllSongFromPlaylist()) {
            this.userService.save(user);
        }
    }

    private User getUserById(Long userId) {
        return this.userService.getUserById(userId);
    }

    private Song getSongById(Long songId) {
        return this.songService.getSongById(songId);
    }
}
