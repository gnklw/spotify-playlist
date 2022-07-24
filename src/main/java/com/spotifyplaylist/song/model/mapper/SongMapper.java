package com.spotifyplaylist.song.model.mapper;

import com.spotifyplaylist.song.model.dto.AddSongDTO;
import com.spotifyplaylist.song.model.dto.SongDTO;
import com.spotifyplaylist.song.model.entity.Song;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {

    SongDTO toSongDTO(Song song);

    Song toSong(AddSongDTO addSongDTO);
}
