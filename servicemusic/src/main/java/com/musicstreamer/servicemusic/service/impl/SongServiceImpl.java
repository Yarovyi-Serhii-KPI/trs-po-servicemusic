package com.musicstreamer.servicemusic.service.impl;

import com.musicstreamer.servicemusic.repo.SongRepo;
import com.musicstreamer.servicemusic.repo.model.Song;
import com.musicstreamer.servicemusic.service.SongService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class SongServiceImpl implements SongService{
    private final SongRepo songRepo;

    public List<Song> fetchAllSongs(){
        return songRepo.findAll();
    }

    public Song fetchSongById(long id) throws IllegalArgumentException {
        final Optional<Song> optSong = songRepo.findById(id);

        if (optSong.isPresent())
            return optSong.get();
        else
            throw new IllegalArgumentException("Invalid song ID");
    }

    public long createSong(String name, String author, int length, int score, int playlist) {
        final Song song = new Song(name, author, length, score, playlist);
        final Song savedSong = songRepo.save(song);

        return savedSong.getId();
    }

    public void updateSong(long id, String name, String author, int length, int score, int playlist) throws IllegalArgumentException {
        final Optional<Song> optSong = songRepo.findById(id);

        if (optSong.isEmpty())
            throw new IllegalArgumentException("Invalid song ID");

        final Song song = optSong.get();
        if (name != null && !name.isBlank()) song.setName(name);
        if (author != null && !author.isBlank()) song.setAuthor(author);
        if (length > 0) song.setLength(length);
        song.setScore(score);
        if (playlist > 0){
            song.setPlaylist(playlist);
        }
        songRepo.save(song);
    }

    public void deleteSong(long id) {
        songRepo.deleteById(id);
    }
}
