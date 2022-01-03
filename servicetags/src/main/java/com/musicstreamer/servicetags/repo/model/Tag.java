package com.musicstreamer.servicetags.repo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tags")
public final class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int song;

    public Tag() {
    }

    public Tag(String name, int song) {
        this.name = name;
        this.song = song;
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

    public int getSong() {return song; }

    public void setSong(int song) {this.song = song; }
}
