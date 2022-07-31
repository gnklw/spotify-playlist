package com.spotifyplaylist.playlist.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PlaylistService {

    void addSongToUser(Long userId, Long songId);

    void removeSongFromUser(Long userId, Long songId);

    void deleteAllSongs(Long userId);
}
