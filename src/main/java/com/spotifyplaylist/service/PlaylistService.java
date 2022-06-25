package com.spotifyplaylist.service;

public interface PlaylistService {

     void addSong(Long songId, Long userId);

     void removeSong(Long songId, Long userId);

     void deleteAll(Long userId);
}
