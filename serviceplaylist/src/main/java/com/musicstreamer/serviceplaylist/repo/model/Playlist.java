package com.musicstreamer.serviceplaylist.repo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "playlists")
public final class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int size;

    public Playlist() {
    }

    public Playlist(String name, int size) {
        this.name = name;
        this.size = size;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
