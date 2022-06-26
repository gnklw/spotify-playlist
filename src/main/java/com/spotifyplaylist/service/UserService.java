package com.spotifyplaylist.service;

import com.spotifyplaylist.model.entity.User;

public interface UserService {

    void save(User user);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    void addSongToUser(Long userId, Long songId);

    void removeSongFromUser(Long userId, Long songId);

    void deleteAllSongs(Long userId);
}
