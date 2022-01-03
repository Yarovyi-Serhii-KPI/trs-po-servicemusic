package com.musicstreamer.serviceplaylist.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.musicstreamer.serviceplaylist.service.impl.PlaylistServiceImpl;
import com.musicstreamer.serviceplaylist.repo.model.Playlist;
import com.musicstreamer.serviceplaylist.api.dto.PlaylistDto;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/playlists")
@RestController
public class PlaylistController {

    private final PlaylistServiceImpl playlistServiceImpl;

    @GetMapping
    public ResponseEntity<List<Playlist>> index(){
        final List<Playlist> playlists = playlistServiceImpl.fetchAllPlaylists();
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> showById(@PathVariable long id) {
        try {
            final Playlist playlist = playlistServiceImpl.fetchPlaylistById(id);

            return ResponseEntity.ok(playlist);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PlaylistDto playlistDto){
        final String name = playlistDto.name();
        final int size = playlistDto.size();
        final long playlistId = playlistServiceImpl.createPlaylist(name, size);
        final String playlistURI = String.format("/playlists/%d", playlistId);

        return ResponseEntity.created(URI.create(playlistURI)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody PlaylistDto playlistDto) {
        final String name = playlistDto.name();
        final int size = playlistDto.size();

        try {
            playlistServiceImpl.updatePlaylist(id, name, size);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        playlistServiceImpl.deletePlaylist(id);

        return ResponseEntity.noContent().build();
    }
}
