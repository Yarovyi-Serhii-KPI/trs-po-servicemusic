package com.musicstreamer.serviceplaylist.api.dto;

import java.util.List;

public class PlaylistDto {
    private String name;
    private int size;

    PlaylistDto(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String name() { return name; }
    public int size() { return size; }
}
