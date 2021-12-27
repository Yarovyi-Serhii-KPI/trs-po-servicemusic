package com.musicstreamer.servicemusic.repo;

import com.musicstreamer.servicemusic.repo.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepo extends JpaRepository<Playlist, Long> {
}