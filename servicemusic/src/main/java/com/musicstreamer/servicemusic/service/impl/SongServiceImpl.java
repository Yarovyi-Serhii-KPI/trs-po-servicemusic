package com.musicstreamer.servicemusic.service.impl;

import com.musicstreamer.servicemusic.api.dto.PlaylistDto;
import com.musicstreamer.servicemusic.repo.SongRepo;
import com.musicstreamer.servicemusic.repo.model.Song;
import com.musicstreamer.servicemusic.service.SongService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class SongServiceImpl implements SongService{
    private final SongRepo songRepo;
    private final String playlistUrl = "http://serviceplaylist:8081/playlists/";

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

    public long createSong(String name, String author, int length, int score, long playlist) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpEntity<Long> request = new HttpEntity<>(playlist);
        try {
            final ResponseEntity<PlaylistDto> response = restTemplate.exchange(playlistUrl + playlist, HttpMethod.GET, request, PlaylistDto.class);
        } catch(HttpClientErrorException e) { throw new IllegalArgumentException(String.format("Playlist with given id = %d was not found", playlist));}

        final Song song = new Song(name, author, length, score, playlist);
        final Song savedSong = songRepo.save(song);

        return savedSong.getId();
    }

    public void updateSong(long id, String name, String author, int length, int score) throws IllegalArgumentException {
        final Optional<Song> optSong = songRepo.findById(id);

        if (optSong.isEmpty())
            throw new IllegalArgumentException("Invalid song ID");

        final Song song = optSong.get();
        if (name != null && !name.isBlank()) song.setName(name);
        if (author != null && !author.isBlank()) song.setAuthor(author);
        if (length > 0) song.setLength(length);
        song.setScore(score);
        songRepo.save(song);
    }

    public void deleteSong(long id) {
        songRepo.deleteById(id);
    }
}
