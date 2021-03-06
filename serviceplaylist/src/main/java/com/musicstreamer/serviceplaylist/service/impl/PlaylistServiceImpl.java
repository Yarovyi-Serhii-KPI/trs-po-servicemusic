package com.musicstreamer.serviceplaylist.service.impl;

import com.musicstreamer.serviceplaylist.repo.PlaylistRepo;
import com.musicstreamer.serviceplaylist.repo.model.Playlist;
import com.musicstreamer.serviceplaylist.service.PlaylistService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepo playlistRepo;

    public List<Playlist> fetchAllPlaylists(){
        return playlistRepo.findAll();
    }

    public Playlist fetchPlaylistById(long id) throws IllegalArgumentException {
        final Optional<Playlist> optPlaylist = playlistRepo.findById(id);

        if (optPlaylist.isPresent())
            return optPlaylist.get();
        else
            throw new IllegalArgumentException("Invalid playlist ID");
    }

    public long createPlaylist(String name, int size) {
        final Playlist playlist = new Playlist(name, size);
        final Playlist savedPlaylist = playlistRepo.save(playlist);

        return savedPlaylist.getId();
    }

    public void updatePlaylist(long id, String name, int size) throws IllegalArgumentException {
        final Optional<Playlist> optPlaylist = playlistRepo.findById(id);

        if (optPlaylist.isEmpty())
            throw new IllegalArgumentException("Invalid playlist ID");

        final Playlist playlist = optPlaylist.get();
        if (name != null && !name.isBlank()) playlist.setName(name);
        if (size >0) playlist.setSize(size);
        playlistRepo.save(playlist);
    }

    public void deletePlaylist(long id) {
        playlistRepo.deleteById(id);
    }
}
