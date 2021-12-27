package com.musicstreamer.servicemusic.api.dto;

import java.util.List;

public class SongDto{
    private String name;
    private String author;
    private int length;
    private int score;
    private List<Integer> tags;

    SongDto(String name, String author, int length, int score, List<Integer> tags) {
        this.name = name;
        this.author = author;
        this.length = length;
        this.score = score;
        this.tags = tags;
    }

    public  String name() { return name; }
    public String author() { return author; }
    public int length() {return length; }
    public int score() {return score; }
    public List<Integer> tags() {return tags; }
}
