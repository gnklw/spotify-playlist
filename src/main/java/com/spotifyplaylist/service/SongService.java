package com.spotifyplaylist.service;

import com.spotifyplaylist.model.dto.AddSongDTO;
import com.spotifyplaylist.model.dto.SongDTO;
import com.spotifyplaylist.model.entity.Song;
import com.spotifyplaylist.model.entity.Styles;

import java.util.Set;

public interface SongService {

     void addSong(AddSongDTO addSongDTO);

     Song getSongById(Long id);

     Set<SongDTO> getSongsByStyle(Styles styleName);

     Set<SongDTO> getPlaylist(Long userId);
}
