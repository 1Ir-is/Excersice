package com.example.music_app.service;

import com.example.music_app.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();
    Song findById(Long id);
    void save(Song song);
    void delete(Long id);
}
