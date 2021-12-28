package com.musicstreamer.servicemusic.repo;

import com.musicstreamer.servicemusic.repo.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepo extends JpaRepository<Song, Long> {
}
