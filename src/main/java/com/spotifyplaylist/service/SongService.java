package com.spotifyplaylist.service;

import com.spotifyplaylist.model.dto.AddSongDTO;
import com.spotifyplaylist.model.dto.SongDTO;
import com.spotifyplaylist.model.entity.Song;
import com.spotifyplaylist.model.entity.Style;

import java.util.Set;

public interface SongService {

     void addSong(AddSongDTO addSongDTO);

     Song findSongById(Long id);

     Set<SongDTO> findSongsByStyle(Style style);

     Set<SongDTO> getPlaylist(Long id);
}
