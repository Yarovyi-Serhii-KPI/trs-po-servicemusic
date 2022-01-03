package com.musicstreamer.serviceplaylist.service;

import com.musicstreamer.serviceplaylist.repo.model.Playlist;

import java.util.List;

public interface PlaylistService {
    List<Playlist> fetchAllPlaylists();
    Playlist fetchPlaylistById(long id) throws IllegalArgumentException;
    long createPlaylist(String name, int size);
    void updatePlaylist(long id, String name, int size) throws IllegalArgumentException;
    void deletePlaylist(long id);

}
