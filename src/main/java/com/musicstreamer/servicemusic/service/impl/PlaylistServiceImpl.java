package com.musicstreamer.servicemusic.service.impl;

import com.musicstreamer.servicemusic.repo.PlaylistRepo;
import com.musicstreamer.servicemusic.repo.model.Playlist;
import com.musicstreamer.servicemusic.service.PlaylistService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepo playlistRepo;
    private final SongServiceImpl songService;

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

    public long createPlaylist(String name, int size, List<Integer> songs) {
        final Playlist playlist = new Playlist(name, size, songs);
        final Playlist savedPlaylist = playlistRepo.save(playlist);

        return savedPlaylist.getId();
    }

    public void updatePlaylist(long id, String name, int size, List<Integer> songs) throws IllegalArgumentException {
        final Optional<Playlist> optPlaylist = playlistRepo.findById(id);

        if (optPlaylist.isEmpty())
            throw new IllegalArgumentException("Invalid playlist ID");

        final Playlist playlist = optPlaylist.get();
        if (name != null && !name.isBlank()) playlist.setName(name);
        if (size >0) playlist.setSize(size);
        if (songs != null && !songs.isEmpty()){
            for(Integer songId : songs){
                songService.fetchSongById(songId);
            }
            playlist.setSongs(songs);
        }
        playlistRepo.save(playlist);
    }

    public void deletePlaylist(long id) {
        playlistRepo.deleteById(id);
    }
}
