package com.example.upload_bai_hat.service;

import com.example.upload_bai_hat.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();
    void save(Song song);
}