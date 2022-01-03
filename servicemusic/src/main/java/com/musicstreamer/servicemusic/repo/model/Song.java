package com.musicstreamer.servicemusic.repo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "songs")
public final class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String author;
    private int length;
    private int score;
    private long playlist;

    public Song() {
    }

    public Song(String name, String author, int length, int score, long playlist) {
        this.name = name;
        this.author = author;
        this.length = length;
        this.score = score;
        this.playlist = playlist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getPlaylist() {
        return playlist;
    }

    public void setPlaylist(long playlist) {
        this.playlist = playlist;
    }
}
