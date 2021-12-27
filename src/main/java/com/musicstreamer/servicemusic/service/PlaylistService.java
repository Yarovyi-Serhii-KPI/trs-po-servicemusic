package com.musicstreamer.servicemusic.service;

import com.musicstreamer.servicemusic.repo.model.Playlist;

import java.util.List;

public interface PlaylistService {
    List<Playlist> fetchAllPlaylists();
    Playlist fetchPlaylistById(long id) throws IllegalArgumentException;
    long createPlaylist(String name, int size, List<Integer> songs);
    void updatePlaylist(long id, String name, int size, List<Integer> songs) throws IllegalArgumentException;
    void deletePlaylist(long id);

}
