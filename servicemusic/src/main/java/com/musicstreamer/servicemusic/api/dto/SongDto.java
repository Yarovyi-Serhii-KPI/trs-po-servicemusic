package com.musicstreamer.servicemusic.api.dto;

import java.util.List;

public class SongDto{
    private String name;
    private String author;
    private int length;
    private int score;
    private long playlist;

    SongDto(String name, String author, int length, int score, long playlist) {
        this.name = name;
        this.author = author;
        this.length = length;
        this.score = score;
        this.playlist = playlist;
    }

    public  String name() { return name; }
    public String author() { return author; }
    public int length() {return length; }
    public int score() {return score; }
    public long playlist() {return playlist; }
}
