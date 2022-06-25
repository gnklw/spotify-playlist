package com.spotifyplaylist.service;

import com.spotifyplaylist.model.dto.RegisterDTO;
import com.spotifyplaylist.model.dto.UserDTO;
import com.spotifyplaylist.model.entity.Song;

public interface AuthService {

     UserDTO findUserByUsername(String username);

     UserDTO findUserByEmail(String email);

     boolean checkCredentials(String username, String password);

     void login(String username);

     void register(RegisterDTO registerDTO);

     void logout();

     void addSongToUser(Long userId, Song song);

     void removeSongFromUser(Long userId, Long songId);

     void deleteAllSongs(Long userId);
}
