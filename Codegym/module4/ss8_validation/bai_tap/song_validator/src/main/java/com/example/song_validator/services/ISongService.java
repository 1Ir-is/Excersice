package com.example.song_validator.services;

import com.example.song_validator.dtos.SongDTO;
import com.example.song_validator.models.Song;

import java.util.List;

public interface ISongService {
    void saveSong(SongDTO songDTO);
    SongDTO findById(Long id);
    void updateSong(Long id, SongDTO songDTO);
    List<Song> findAll();
    void deleteById(Long id);
}