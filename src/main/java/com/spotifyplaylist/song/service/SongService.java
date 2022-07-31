package com.spotifyplaylist.song.service;

import com.spotifyplaylist.song.model.dto.AddSongDTO;
import com.spotifyplaylist.song.model.dto.SongDTO;
import com.spotifyplaylist.song.model.entity.Song;
import com.spotifyplaylist.style.enumeration.Styles;

import java.util.Set;

public interface SongService {

     void addSong(AddSongDTO addSongDTO);

     Song getSongById(Long id);

     Set<SongDTO> getSongsByStyle(Styles styleName);

     Set<SongDTO> getPlaylistByUserId(Long userId);

     String calcPlaylistSongsDuration(Set<SongDTO> playlist);
}
