package com.musicstreamer.servicemusic.repo;

import com.musicstreamer.servicemusic.repo.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag, Long> {
}