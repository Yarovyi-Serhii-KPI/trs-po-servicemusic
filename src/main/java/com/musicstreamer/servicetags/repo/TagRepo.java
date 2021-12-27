package com.musicstreamer.servicetags.repo;

import com.musicstreamer.servicetags.repo.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag, Long> {
}