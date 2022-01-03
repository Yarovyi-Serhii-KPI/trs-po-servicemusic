package com.musicstreamer.servicemusic.service;

import com.musicstreamer.servicemusic.repo.model.Song;

import java.util.List;

public interface SongService {
    List<Song> fetchAllSongs();
    Song fetchSongById(long id) throws IllegalArgumentException;
    long createSong(String name, String author, int length, int score, int playlist);
    void updateSong(long id, String name, String author, int length, int score, int playlist) throws IllegalArgumentException;
    void deleteSong(long id);
}
