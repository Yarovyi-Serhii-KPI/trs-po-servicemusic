package com.musicstreamer.servicemusic.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.musicstreamer.servicemusic.service.impl.SongServiceImpl;
import com.musicstreamer.servicemusic.repo.model.Song;
import com.musicstreamer.servicemusic.api.dto.SongDto;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/songs")
@RestController
public class SongController {

    private final SongServiceImpl songServiceImpl;

    @GetMapping
    public ResponseEntity<List<Song>> index(){
        final List<Song> songs = songServiceImpl.fetchAllSongs();
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> showById(@PathVariable long id) {
        try {
            final Song song = songServiceImpl.fetchSongById(id);

            return ResponseEntity.ok(song);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody SongDto songDto){
        final String name = songDto.name();
        final String author = songDto.author();
        final int length = songDto.length();
        final int score = songDto.score();
        final int playlist = songDto.playlist();
        final long songId = songServiceImpl.createSong(name, author, length, score, playlist);
        final String songURI = String.format("/songs/%d", songId);

        return ResponseEntity.created(URI.create(songURI)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody SongDto songDto) {
        final String name = songDto.name();
        final String author = songDto.author();
        final int length = songDto.length();
        final int score = songDto.score();
        final int playlist = songDto.playlist();

        try {
            songServiceImpl.updateSong(id, name, author, length, score, playlist);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        songServiceImpl.deleteSong(id);

        return ResponseEntity.noContent().build();
    }
}
