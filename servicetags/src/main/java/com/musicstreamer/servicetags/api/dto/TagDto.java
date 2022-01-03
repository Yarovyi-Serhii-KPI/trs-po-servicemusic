package com.musicstreamer.servicetags.api.dto;

import java.util.List;

public class TagDto {
    private String name;
    private int song;

    TagDto(String name, int song) {
        this.name = name;
        this.song = song;
    }

    public String name() { return name; }

    public int song() {return song; }
}
