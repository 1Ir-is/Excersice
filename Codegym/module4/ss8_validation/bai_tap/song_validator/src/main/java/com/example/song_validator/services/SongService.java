package com.example.song_validator.services;

import com.example.song_validator.dtos.SongDTO;
import com.example.song_validator.models.Song;
import com.example.song_validator.repositories.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService implements ISongService {

    @Autowired
    private ISongRepository songRepository;

    @Override
    public void saveSong(SongDTO songDTO) {
        Song song = new Song();
        song.setName(songDTO.getName());
        song.setArtist(songDTO.getArtist());
        song.setGenre(songDTO.getGenre());
        songRepository.save(song);
    }

    @Override
    public SongDTO findById(Long id) {
        Song song = songRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Song not found")
        );
        SongDTO songDTO = new SongDTO();
        songDTO.setName(song.getName());
        songDTO.setArtist(song.getArtist());
        songDTO.setGenre(song.getGenre());
        return songDTO;
    }

    @Override
    public void updateSong(Long id, SongDTO songDTO) {
        Song song = songRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Song not found")
        );
        song.setName(songDTO.getName());
        song.setArtist(songDTO.getArtist());
        song.setGenre(songDTO.getGenre());
        songRepository.save(song);
    }

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if (!songRepository.existsById(id)) {
            throw new RuntimeException("Song not found");
        }
        songRepository.deleteById(id);
    }
}