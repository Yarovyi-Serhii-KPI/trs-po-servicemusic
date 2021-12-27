package com.musicstreamer.serviceplaylist.repo;

import com.musicstreamer.serviceplaylist.repo.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepo extends JpaRepository<Playlist, Long> {
}