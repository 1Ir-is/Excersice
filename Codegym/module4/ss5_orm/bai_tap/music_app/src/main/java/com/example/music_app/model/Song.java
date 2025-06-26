package com.example.music_app.model;

import javax.persistence.*;

@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "song_name")
    private String name;

    @Column(name = "song_artist")
    private String artist;

    @Column(name = "song_genre")
    private String genre;

    @Column(name = "file_path")
    private String filePath;

    public Song() {
    }

    public Song(Long id, String name, String artist, String genre, String filePath) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
