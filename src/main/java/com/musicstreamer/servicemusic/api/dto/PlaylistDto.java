package com.musicstreamer.servicemusic.api.dto;

import java.util.List;

public class PlaylistDto {
    private String name;
    private int size;
    private List<Integer> songs;

    PlaylistDto(String name, int size, List<Integer> songs) {
        this.name = name;
        this.size = size;
        this.songs = songs;
    }

    public String name() { return name; }
    public int size() { return size; }
    public List<Integer> songs() {return songs; }
}
